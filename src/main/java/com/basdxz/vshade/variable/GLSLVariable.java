package com.basdxz.vshade.variable;

import com.basdxz.vshade.type.GLSLResource;
import com.basdxz.vshade.type.GLSLType;

import java.util.StringJoiner;

public interface GLSLVariable extends GLSLType, GLSLResource {
    int matrixStride();

    default boolean variableEqual(GLSLVariable other) {
        if (other == null)
            return false;
        if (other == this)
            return true;
        if (boxedType() != other.boxedType())
            return false;
        if (arraySize() != other.arraySize())
            return false;
        return typeName().equals(other.typeName());
    }

    @Override
    default String typeToString() {
        return GLSLType.super.typeToString() + "," +
                new StringJoiner(", ", GLSLVariable.class.getSimpleName() + "[", "]")
                        .add("arrayStride=" + arrayStride())
                        .add("matrixStride=" + matrixStride());
    }
}
