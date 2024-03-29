package com.basdxz.vshade.variable;


import com.basdxz.vbuffers.common.Disposable;
import com.basdxz.vshade.type.GLSLTypeWrapper;

import static com.basdxz.vbuffers.common.Constants.NULL_INT;
import static com.basdxz.vbuffers.common.Constants.NULL_STRING;

public interface GLSLVariableLink extends GLSLVariable, GLSLTypeWrapper, Disposable {
    boolean link();

    @Override
    default String name() {
        if (linked())
            return variable().name();
        return NULL_STRING;
    }

    @Override
    default int program() {
        if (linked())
            return variable().program();
        return NULL_INT;
    }

    @Override
    default int location() {
        if (linked())
            return variable().location();
        return NULL_INT;
    }

    @Override
    default int offset() {
        if (linked())
            return variable().offset();
        return NULL_INT;
    }

    @Override
    default int stride() {
        if (linked())
            return variable().stride();
        return NULL_INT;
    }

    @Override
    default int arraySize() {
        if (linked())
            return variable().arraySize();
        return NULL_INT;
    }

    @Override
    default int arrayStride() {
        if (linked())
            return variable().arrayStride();
        return NULL_INT;
    }

    @Override
    default int matrixStride() {
        if (linked())
            return variable().matrixStride();
        return NULL_INT;
    }

    boolean linked();

    GLSLVariable variable();
}
