package com.basdxz.vshade.variable.floats;


import com.basdxz.vshade.type.GLSLType;
import com.basdxz.vshade.type.GLSLTypes;
import com.basdxz.vshade.variable.LinkedVariable;
import lombok.*;
import lombok.experimental.*;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.lwjgl.opengl.*;

import java.nio.ByteBuffer;

@SuperBuilder
public class GLFloatVec3 extends LinkedVariable<GLFloatVec3, Vector3fc, Vector3f> {
    @Override
    public GLSLType wrappedType() {
        return GLSLTypes.FLOAT_VEC3;
    }

    @Override
    public Vector3f newDefaultOutput() {
        return new Vector3f();
    }

    @Override
    protected void setImpl(int byteOffset, @NonNull Vector3fc input) {
        input.get(byteOffset, buffer);
    }

    @Override
    protected Vector3f getImpl(int byteOffset, @NonNull Vector3f output) {
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
