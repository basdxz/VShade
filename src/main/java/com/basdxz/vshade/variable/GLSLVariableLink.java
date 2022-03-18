package com.basdxz.vshade.variable;


import com.basdxz.vbuffers.common.Disposable;
import com.basdxz.vshade.query.ShaderQuery;
import com.basdxz.vshade.type.GLSLTypeWrapper;

public interface GLSLVariableLink extends GLSLVariable, GLSLTypeWrapper, Disposable {
    boolean link();

    @Override
    default String name() {
        if (linked())
            return variable().name();
        return ShaderQuery.NULL_STRING;
    }

    @Override
    default int program() {
        if (linked())
            return variable().program();
        return ShaderQuery.NULL_INT;
    }

    @Override
    default int location() {
        if (linked())
            return variable().location();
        return ShaderQuery.NULL_INT;
    }

    @Override
    default int offset() {
        if (linked())
            return variable().offset();
        return ShaderQuery.NULL_INT;
    }

    @Override
    default int stride() {
        if (linked())
            return variable().stride();
        return ShaderQuery.NULL_INT;
    }

    @Override
    default int arraySize() {
        if (linked())
            return variable().arraySize();
        return ShaderQuery.NULL_INT;
    }

    @Override
    default int arrayStride() {
        if (linked())
            return variable().arrayStride();
        return ShaderQuery.NULL_INT;
    }

    @Override
    default int matrixStride() {
        if (linked())
            return variable().matrixStride();
        return ShaderQuery.NULL_INT;
    }

    boolean linked();

    GLSLVariable variable();
}
