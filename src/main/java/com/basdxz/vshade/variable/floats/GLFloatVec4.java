package com.basdxz.vshade.variable.floats;


import com.basdxz.vshade.type.GLSLType;
import com.basdxz.vshade.type.GLSLTypes;
import com.basdxz.vshade.variable.LinkedVariable;
import lombok.*;
import lombok.experimental.*;
import org.joml.Vector4f;
import org.joml.Vector4fc;
import org.lwjgl.opengl.*;

import java.nio.ByteBuffer;

@SuperBuilder
public class GLFloatVec4 extends LinkedVariable<GLFloatVec4, Vector4fc, Vector4f> {
    @Override
    public GLSLType wrappedType() {
        return GLSLTypes.FLOAT_VEC3;
    }

    @Override
    public Vector4f newDefaultOutput() {
        return new Vector4f();
    }

    @Override
    protected void setImpl(int byteOffset, @NonNull Vector4fc input) {
        input.get(byteOffset, buffer);
    }

    @Override
    protected Vector4f getImpl(int byteOffset, @NonNull Vector4f output) {
        return output.set(byteOffset, buffer);
    }

    @Override
    protected void uploadUniformImpl(int program, int location, boolean transpose, @NonNull ByteBuffer input) {
        GL41.glProgramUniform3(program, location, input.asFloatBuffer());
    }

    @Override
    protected void downloadUniformImpl(int program, int location, @NonNull ByteBuffer output) {
        GL20.glGetUniform(program, location, output.asFloatBuffer());
    }
}
