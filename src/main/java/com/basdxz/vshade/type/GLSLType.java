package com.basdxz.vshade.type;

import java.util.StringJoiner;

public interface GLSLType {
    String typeName();

    int primitiveType();

    int boxedType();

    boolean isOpaque();

    int rows();

    int columns();

    int units();

    int unitSize();

    int rowSize();

    int columnSize();

    int typeSize();

    default String typeToString() {
        return new StringJoiner(", ", GLSLType.class.getSimpleName() + "[", "]")
                .add("typeName='" + typeName() + "'")
                .add("primitiveType=" + primitiveType())
                .add("boxedType=" + boxedType())
                .add("isOpaque=" + isOpaque())
                .add("rows=" + rows())
                .add("columns=" + columns())
                .add("unitSize=" + unitSize())
                .add("rowSize=" + rowSize())
                .add("columnSize=" + columnSize())
                .add("typeSize=" + typeSize())
                .toString();
    }
}
