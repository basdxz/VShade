package com.basdxz.vshade.type;


import com.basdxz.vshade.query.ShaderQuery;

public interface GLSLTypeWrapper extends GLSLType {
    @Override
    default String typeName() {
        if (wrappedType() != null)
            return wrappedType().typeName();
        return ShaderQuery.NULL_STRING;
    }

    @Override
    default int primitiveType() {
        if (wrappedType() != null)
            return wrappedType().primitiveType();
        return ShaderQuery.NULL_INT;
    }

    @Override
    default int boxedType() {
        if (wrappedType() != null)
            return wrappedType().boxedType();
        return ShaderQuery.NULL_INT;
    }

    @Override
    default boolean isOpaque() {
        if (wrappedType() != null)
            return wrappedType().isOpaque();
        return ShaderQuery.NULL_BOOLEAN;
    }

    @Override
    default int rows() {
        if (wrappedType() != null)
            return wrappedType().rows();
        return ShaderQuery.NULL_INT;
    }

    @Override
    default int columns() {
        if (wrappedType() != null)
            return wrappedType().columns();
        return ShaderQuery.NULL_INT;
    }

    @Override
    default int units() {
        if (wrappedType() != null)
            return wrappedType().units();
        return ShaderQuery.NULL_INT;
    }

    @Override
    default int unitSize() {
        if (wrappedType() != null)
            return wrappedType().unitSize();
        return ShaderQuery.NULL_INT;
    }

    @Override
    default int rowSize() {
        if (wrappedType() != null)
            return wrappedType().rowSize();
        return ShaderQuery.NULL_INT;
    }

    @Override
    default int columnSize() {
        if (wrappedType() != null)
            return wrappedType().columnSize();
        return ShaderQuery.NULL_INT;
    }

    @Override
    default int typeSize() {
        if (wrappedType() != null)
            return wrappedType().typeSize();
        return ShaderQuery.NULL_INT;
    }

    GLSLType wrappedType();
}
