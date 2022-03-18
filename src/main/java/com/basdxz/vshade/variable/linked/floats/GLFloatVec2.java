package com.basdxz.vshade.variable.linked.floats;

import com.basdxz.vshade.type.GLSLType;
import com.basdxz.vshade.type.GLSLTypes;
import com.basdxz.vshade.variable.linked.LinkedVariable;
import lombok.*;
import lombok.experimental.*;
import org.joml.Vector2f;
import org.joml.Vector2fc;
import org.lwjgl.opengl.*;

import java.nio.ByteBuffer;

@SuperBuilder
public class GLFloatVec2 extends LinkedVariable<GLFloatVec2, Vector2fc, Vector2f> {
    @Override
    public GLSLType wrappedType() {
        return GLSLTypes.FLOAT_VEC2;
    }

    @Override
    public Vector2f newDefaultOutput() {
        return new Vector2f();
    }

    @Override
    protected void setImpl(int byteOffset, @NonNull Vector2fc input) {
        input.get(byteOffset, buffer);
    }

    @Override
    protected Vector2f getImpl(int byteOffset, @NonNull Vector2f output) {
        return output.set(byteOffset, buffer);
    }

    @Override
    protected void uploadUniformImpl(int program, int location, boolean transpose, @NonNull ByteBuffer input) {
        GL41.glProgramUniform3fv(program, location, input.asFloatBuffer());
    }

    @Override
    protected void downloadUniformImpl(int program, int location, @NonNull ByteBuffer output) {
        GL20.glGetUniformfv(program, location, output.asFloatBuffer());
    }
}
