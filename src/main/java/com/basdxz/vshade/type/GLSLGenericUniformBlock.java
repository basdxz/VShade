package com.basdxz.vshade.type;

import com.basdxz.vshade.variable.GLSLVariable;
import lombok.*;

import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.IntStream;

@Getter
public class GLSLGenericUniformBlock implements GLSLUniformBlock {
    protected final List<GLSLVariable> variables;
    protected final String name;
    protected final int program;
    protected final int location;
    protected final int arraySize;
    protected final int arrayStride;
    protected final int stride;
    protected final int offset = 0;

    public GLSLGenericUniformBlock(List<GLSLVariable> variables, String name,
                                   int program, int location, int arraySize, int arrayStride) {
        this.variables = variables;
        this.name = name;
        this.program = program;
        this.location = location;
        this.arraySize = arraySize;
        this.arrayStride = arrayStride;
        this.stride = arrayStride * arraySize;
    }

    @Override
    public List<GLSLVariable> variables() {
        return Collections.unmodifiableList(variables);
    }

    @Override
    public String toString() {
        val variable = new StringJoiner(", ", "[", "]");
        IntStream.range(0, variables.size())
                .mapToObj(i -> String.format("%d=%s", i, variables.get(i).toString()))
                .forEach(variable::add);

        return new StringJoiner(", ", GLSLGenericUniformBlock.class.getSimpleName() + "[", "]")
                .add("variables=" + variable)
                .add("name='" + name + "'")
                .add("location=" + location)
                .add("arraySize=" + arraySize)
                .add("stride=" + stride)
                .add("offset=" + offset)
                .toString();
    }
}
