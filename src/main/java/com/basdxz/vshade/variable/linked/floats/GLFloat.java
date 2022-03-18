package com.basdxz.vshade.variable.linked.floats;


import com.basdxz.vshade.type.GLSLType;
import com.basdxz.vshade.type.GLSLTypes;
import com.basdxz.vshade.variable.linked.LinkedVariable;
import lombok.*;
import lombok.experimental.*;
import org.lwjgl.opengl.*;

import java.nio.ByteBuffer;

import static com.basdxz.vbuffers.common.Constants.FLOAT_SIZE;


@SuperBuilder
public class GLFloat extends LinkedVariable<GLFloat, Float, Float> {
    @Override
    public GLSLType wrappedType() {
        return GLSLTypes.FLOAT;
    }

    @Override
    public Float newDefaultOutput() {
        return 0F;
    }

    @Override
    protected void setImpl(int byteOffset, @NonNull Float input) {
        buffer.asFloatBuffer().put(byteOffset / FLOAT_SIZE, input);
    }

    @Override
    protected Float getImpl(int byteOffset, @NonNull Float output) {
        return buffer.asFloatBuffer().get(byteOffset / FLOAT_SIZE);
    }

    @Override
    protected void uploadUniformImpl(int program, int location, boolean transpose, @NonNull ByteBuffer input) {
        GL41.glProgramUniform1(program, location, input.asFloatBuffer());
    }

    @Override
    protected void downloadUniformImpl(int program, int location, @NonNull ByteBuffer output) {
        GL20.glGetUniform(program, location, output.asFloatBuffer());
    }
}
