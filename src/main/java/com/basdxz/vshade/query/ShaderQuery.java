package com.basdxz.vshade.query;


import com.basdxz.vbuffers.common.MemUtils;
import com.basdxz.vshade.introspection.ShaderInfo;
import com.basdxz.vshade.type.GLSLTypes;
import com.basdxz.vshade.variable.GLSLGenericUniformBlock;
import com.basdxz.vshade.variable.GLSLGenericVariable;
import com.basdxz.vshade.variable.GLSLUniformBlock;
import com.basdxz.vshade.variable.GLSLVariable;
import lombok.*;
import org.lwjgl.opengl.*;

import java.nio.IntBuffer;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.basdxz.vbuffers.common.Constants.NULL_INT;

//TODO: Support for Shader Storage Blocks Objects (SSBO)
public class ShaderQuery implements IShaderQuery {
    protected static final Pattern ARRAY_BRACKET_PATTERN = Pattern.compile("\\[\\d*]");
    protected static final IntBuffer BLOCK_INDEX_PROP = MemUtils.newBufferFromValue(GL43.GL_BLOCK_INDEX);
    protected static final IntBuffer LOCATION_PROP = MemUtils.newBufferFromValue(GL43.GL_LOCATION);
    protected static final IntBuffer VARIABLE_PROP = MemUtils.newBufferFromValue(GL43.GL_TYPE, GL43.GL_ARRAY_SIZE);
    protected static final IntBuffer UNIFORM_BLOCK_PROP = MemUtils.newBufferFromValue(GL43.GL_NUM_ACTIVE_VARIABLES, GL43.GL_BUFFER_DATA_SIZE);
    protected static final IntBuffer ACTIVE_VARIABLES_PROP = MemUtils.newBufferFromValue(GL43.GL_ACTIVE_VARIABLES);
    protected static final IntBuffer UNIFORM_VARIABLE_PROP = MemUtils.newBufferFromValue(GL43.GL_TYPE, GL43.GL_LOCATION, GL43.GL_ARRAY_SIZE, GL43.GL_OFFSET, GL43.GL_MATRIX_STRIDE, GL43.GL_ARRAY_STRIDE);

    protected final IntBuffer resultBuffer = MemUtils.newIntBuffer(16);
    protected final int program;
    protected final Map<String, GLSLVariable> inputMap;
    protected final Map<String, GLSLVariable> outputMap;
    protected final Map<String, GLSLVariable> uniformMap;
    protected final Map<String, GLSLUniformBlock> uniformBlockMap;

    public ShaderQuery(int program) {
        this.program = program;
        inputMap = variableMap(GL43.GL_PROGRAM_INPUT);
        outputMap = variableMap(GL43.GL_PROGRAM_OUTPUT);
        uniformMap = uniformMap();
        uniformBlockMap = uniformBlockMap();

        new ShaderInfo(program);
    }

    protected Map<String, GLSLVariable> variableMap(int glTypeEnum) {
        var locationIndexMap = new TreeMap<Integer, Integer>();
        for (int index : getProgramActiveResourceIndices(glTypeEnum)) {
            GL43.glGetProgramResource(program, glTypeEnum, index, LOCATION_PROP, null, resultBuffer);
            locationIndexMap.put(resultBuffer.get(0), index);
        }

        val variableMap = new HashMap<String, GLSLVariable>();
        var byteOffset = 0;
        for (Map.Entry<Integer, Integer> entry : locationIndexMap.entrySet()) {
            val index = entry.getValue();
            val location = entry.getKey();
            val variable = getGLSLVariable(glTypeEnum, index, location, byteOffset);
            byteOffset += variable.typeSize();
            variableMap.put(variable.name(), variable);
        }
        return variableMap;
    }

    protected Map<String, GLSLVariable> uniformMap() {
        val variableMap = new HashMap<String, GLSLVariable>();
        for (int i : getProgramActiveResourceIndices(GL43.GL_UNIFORM)) {
            val variable = getUniformGLSLVariable(i);
            variableMap.put(variable.name(), variable);
        }
        return variableMap;
    }

    protected Map<String, GLSLUniformBlock> uniformBlockMap() {
        val uniformBlockMap = new HashMap<String, GLSLUniformBlock>();

        val nameLocationMap = new HashMap<String, Integer>();
        val nameArraySizeMap = new HashMap<String, Integer>();
        for (int location : getProgramActiveResourceIndices(GL43.GL_UNIFORM_BLOCK)) {
            val name = getProgramResourceName(GL43.GL_UNIFORM_BLOCK, location);

            nameLocationMap.put(name, Math.min(nameLocationMap.getOrDefault(name, 0), location));
            nameLocationMap.put(name, location);
            nameArraySizeMap.put(name, nameArraySizeMap.getOrDefault(name, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : nameLocationMap.entrySet()) {
            final int location = entry.getValue();

            GL43.glGetProgramResource(program, GL43.GL_UNIFORM_BLOCK, location, UNIFORM_BLOCK_PROP, null, resultBuffer);

            val activeVariables = resultBuffer.get(0);
            if (activeVariables < 1)
                continue;

            val arrayStride = resultBuffer.get(1);
            val variableLocations = MemUtils.newIntBuffer(activeVariables);
            GL43.glGetProgramResource(program, GL43.GL_UNIFORM_BLOCK, location, ACTIVE_VARIABLES_PROP, null, variableLocations);

            val variables = getUniformBlockGLSLVariables(MemUtils.newArrayFromBuffer(variableLocations));
            val name = entry.getKey();
            val arraySize = nameArraySizeMap.get(name);
            uniformBlockMap.put(name, new GLSLGenericUniformBlock(variables, name, program, location, arraySize, arrayStride));
        }
        return uniformBlockMap;
    }

    protected int[] getProgramActiveResourceIndices(int glTypeEnum) {
        var locations = IntStream.range(0, activeResourceCount(glTypeEnum));
        if (glTypeEnum == GL43.GL_UNIFORM)
            locations = locations.filter(location -> !resourceExists(glTypeEnum, location, BLOCK_INDEX_PROP));
        return locations.toArray();
    }

    protected int activeResourceCount(int glTypeEnum) {
        return GL43.glGetProgramInterfacei(program, glTypeEnum, GL43.GL_ACTIVE_RESOURCES);
    }

    protected boolean resourceExists(int glTypeEnum, int index, IntBuffer prop) {
        GL43.glGetProgramResource(program, glTypeEnum, index, prop, null, resultBuffer);
        return resultBuffer.get(0) != NULL_INT;
    }

    protected GLSLVariable getGLSLVariable(int glTypeEnum, int index, int location, int byteOffset) {
        GL43.glGetProgramResource(program, glTypeEnum, index, VARIABLE_PROP, null, resultBuffer);

        val type = GLSLTypes.get(resultBuffer.get(0));
        val arraySize = resultBuffer.get(1);
        val name = getProgramResourceName(glTypeEnum, index);
        return new GLSLGenericVariable(type, name, program, location, arraySize, byteOffset);
    }

    protected List<GLSLVariable> getUniformBlockGLSLVariables(int... variableLocations) {
        return Arrays.stream(variableLocations)
                .mapToObj(this::getUniformGLSLVariable)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    protected GLSLVariable getUniformGLSLVariable(int index) {
        GL43.glGetProgramResource(program, GL43.GL_UNIFORM, index, UNIFORM_VARIABLE_PROP, null, resultBuffer);

        val type = GLSLTypes.get(resultBuffer.get(0));
        val location = resultBuffer.get(1);
        val arraySize = resultBuffer.get(2);
        val byteOffset = resultBuffer.get(3);
        val matrixStride = resultBuffer.get(4);
        val arrayStride = resultBuffer.get(5);
        val name = getProgramResourceName(GL43.GL_UNIFORM, index);
        return new GLSLGenericVariable(type, name, program, location, arraySize, byteOffset, matrixStride, arrayStride);
    }

    protected String getProgramResourceName(int glTypeEnum, int index) {
        GL43.glGetProgramResource(program, glTypeEnum, index,
                MemUtils.newBufferFromValue(GL43.GL_NAME_LENGTH), null, resultBuffer);
        return ARRAY_BRACKET_PATTERN
                .matcher(GL43.glGetProgramResourceName(program, glTypeEnum, index, resultBuffer.get(0)))
                .replaceAll("");
    }

    @Override
    public Optional<GLSLVariable> input(String name) {
        return Optional.ofNullable(inputMap.get(name));
    }

    @Override
    public Optional<GLSLVariable> output(String name) {
        return Optional.ofNullable(outputMap.get(name));
    }

    @Override
    public Optional<GLSLVariable> uniform(String name) {
        return Optional.ofNullable(uniformMap.get(name));
    }

    @Override
    public String toString() {
        val inputMap = new StringJoiner(", ", "[", "]");
        this.inputMap.values().forEach(input -> inputMap.add(input.toString()));
        val outputMap = new StringJoiner(", ", "[", "]");
        this.outputMap.values().forEach(input -> outputMap.add(input.toString()));
        val uniformMap = new StringJoiner(", ", "[", "]");
        this.uniformMap.values().forEach(input -> uniformMap.add(input.toString()));

        return new StringJoiner(", ", ShaderQuery.class.getSimpleName() + "[", "]")
                .add("program=" + program)
                .add("inputMap=" + inputMap)
                .add("outputMap=" + outputMap)
                .add("uniformMap=" + uniformMap)
                .add("uniformBlockMap=" + uniformBlockMap)
                .toString();
    }
}
