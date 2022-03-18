package com.basdxz.vshade.variable.linked.floats;


import com.basdxz.vshade.type.GLSLType;
import com.basdxz.vshade.type.GLSLTypes;
import com.basdxz.vshade.variable.linked.LinkedVariable;
import lombok.*;
import lombok.experimental.*;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.lwjgl.opengl.*;

import java.nio.ByteBuffer;

@SuperBuilder
public class GLFloatMat4 extends LinkedVariable<GLFloatMat4, Matrix4fc, Matrix4f> {
    @Override
    public GLSLType wrappedType() {
        return GLSLTypes.FLOAT_MAT4;
    }

    @Override
    public Matrix4f newDefaultOutput() {
        return new Matrix4f();
    }

    @Override
    protected void setImpl(int byteOffset, @NonNull Matrix4fc input) {
        input.get(byteOffset, buffer);
    }

    @Override
    protected Matrix4f getImpl(int byteOffset, @NonNull Matrix4f output) {
        return output.set(byteOffset, buffer);
    }

    @Override
    protected void uploadUniformImpl(int program, int location, boolean transpose, @NonNull ByteBuffer input) {
        GL41.glProgramUniformMatrix4(program, location, transpose, input.asFloatBuffer());
    }

    @Override
    protected void downloadUniformImpl(int program, int location, @NonNull ByteBuffer output) {
        GL20.glGetUniform(program, location, output.asFloatBuffer());
    }
}
