package com.basdxz.vshade.type;

import com.basdxz.vshade.variable.GLSLVariable;
import lombok.*;

import java.nio.ByteBuffer;
import java.util.StringJoiner;

@Getter
public class GLSLGenericVariable implements GLSLVariable, GLSLTypeWrapper {
    protected final GLSLType wrappedType;
    protected final String name;
    protected final int program;
    protected final int location;
    protected final int offset;
    protected final int stride;
    protected final int arraySize;
    protected final int arrayStride;
    protected final int matrixStride;

    public GLSLGenericVariable(@NonNull GLSLType type, @NonNull String name, int program, int location,
                               int arraySize, int offset) {
        this(type, name, program, location, arraySize, offset, -1, -1);
    }

    public GLSLGenericVariable(@NonNull GLSLType type, @NonNull String name, int program, int location, int arraySize,
                               int offset, int matrixStride, int arrayStride) {
        this.wrappedType = type;
        this.name = name;
        this.program = program;
        this.location = location;
        this.offset = offset;
        this.stride = initStride();
        this.arraySize = arraySize;
        this.arrayStride = initArrayStride(arrayStride);
        this.matrixStride = initMatrixStride(matrixStride);
    }

    private int initStride() {
        return arrayStride * arraySize;
    }

    private int initArrayStride(int arrayStride) {
        return arrayStride > 0 ? arrayStride : wrappedType.typeSize();
    }

    private int initMatrixStride(int matrixStride) {
        return matrixStride > 0 ? matrixStride : wrappedType.columnSize();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GLSLGenericVariable.class.getSimpleName() + "[", "]")
                .add("type=" + typeToString())
                .add("name='" + name + "'")
                .add("location=" + location)
                .add("arraySize=" + arraySize)
                .add("byteSize=" + stride)
                .add("byteOffset=" + offset)
                .toString();
    }

    @Override
    public void downloadUniform(int program, int location, ByteBuffer output) {

    }

    @Override
    public void uploadUniform(int program, int location, boolean transpose, ByteBuffer input) {

    }
}
