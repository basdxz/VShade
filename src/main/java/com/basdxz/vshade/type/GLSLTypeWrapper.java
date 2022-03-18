package com.basdxz.vshade.type;


import static com.basdxz.vbuffers.common.Constants.*;

public interface GLSLTypeWrapper extends GLSLType {
    @Override
    default String typeName() {
        if (wrappedType() != null)
            return wrappedType().typeName();
        return NULL_STRING;
    }

    @Override
    default int primitiveType() {
        if (wrappedType() != null)
            return wrappedType().primitiveType();
        return NULL_INT;
    }

    @Override
    default int boxedType() {
        if (wrappedType() != null)
            return wrappedType().boxedType();
        return NULL_INT;
    }

    @Override
    default boolean isOpaque() {
        if (wrappedType() != null)
            return wrappedType().isOpaque();
        return NULL_BOOLEAN;
    }

    @Override
    default int rows() {
        if (wrappedType() != null)
            return wrappedType().rows();
        return NULL_INT;
    }

    @Override
    default int columns() {
        if (wrappedType() != null)
            return wrappedType().columns();
        return NULL_INT;
    }

    @Override
    default int units() {
        if (wrappedType() != null)
            return wrappedType().units();
        return NULL_INT;
    }

    @Override
    default int unitSize() {
        if (wrappedType() != null)
            return wrappedType().unitSize();
        return NULL_INT;
    }

    @Override
    default int rowSize() {
        if (wrappedType() != null)
            return wrappedType().rowSize();
        return NULL_INT;
    }

    @Override
    default int columnSize() {
        if (wrappedType() != null)
            return wrappedType().columnSize();
        return NULL_INT;
    }

    @Override
    default int typeSize() {
        if (wrappedType() != null)
            return wrappedType().typeSize();
        return NULL_INT;
    }

    GLSLType wrappedType();
}
