package com.basdxz.vshade.introspection;


import com.basdxz.vbuffers.common.MemUtils;
import lombok.*;
import org.lwjgl.opengl.*;

import java.nio.IntBuffer;
import java.util.*;
import java.util.regex.Pattern;

import static com.basdxz.vshade.introspection.ResourceProperty.NAME_LENGTH;

public class ShaderInfo {
    protected static final Pattern ARRAY_BRACKET_PATTERN = Pattern.compile("\\[\\d*]");

    protected final int program;
    protected final Map<IShaderInterface, List<IShaderResource>> interfaceResources = new HashMap<>();

    public ShaderInfo(int program) {
        this.program = program;

        for (val shaderInterface : ShaderInterface.values()) {
            val activeResources = activeResources(shaderInterface);
            if (activeResources < 1) {
                interfaceResources.put(shaderInterface, Collections.emptyList());
                break;
            }
            val resources = new ArrayList<IShaderResource>();

            for (var i = 0; i < activeResources; i++) {
                val parameterBuffer = getParameters(shaderInterface, i);
                val parameters = new HashMap<IResourceProperty, IResourceParameter>();
                for (var j = 0; j < shaderInterface.parameterTypes().size(); j++) {
                    val parameterType = shaderInterface.parameterTypes().get(j);
                    parameters.put(parameterType, new ResourceParameter(parameterType, parameterBuffer.get(j)));
                }

                resources.add(new ShaderResource(getName(shaderInterface, i, parameters), shaderInterface, parameters));
            }
            interfaceResources.put(shaderInterface, resources);
        }
    }

    protected int activeResources(IShaderInterface shaderInterface) {
        return GL43.glGetProgramInterfacei(program, shaderInterface.interfaceType(), GL43.GL_ACTIVE_RESOURCES);
    }

    protected IntBuffer getParameters(IShaderInterface shaderInterface, int index) {
        val length = shaderInterface.parameterTypes().size();
        val params = MemUtils.newIntBuffer(length);
        GL43.glGetProgramResource(
                program,
                shaderInterface.interfaceType(),
                index,
                shaderInterface.props(),
                MemUtils.newBufferFromValue(length),
                params);
        return params;
    }

    protected String getName(IShaderInterface shaderInterface, int index,
                             Map<IResourceProperty, IResourceParameter> parameters) {
        val nameLengthParam = parameters.get(NAME_LENGTH);
        if (nameLengthParam == null)
            return "";
        val nameLength = nameLengthParam.value();
        if (nameLength == 0)
            return "";

        return ARRAY_BRACKET_PATTERN
                .matcher(GL43.glGetProgramResourceName(program, shaderInterface.interfaceType(), index, nameLength))
                .replaceAll("");
    }
}
