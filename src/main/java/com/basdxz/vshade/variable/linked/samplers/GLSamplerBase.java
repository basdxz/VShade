package com.basdxz.vshade.variable.linked.samplers;


import com.basdxz.vshade.variable.linked.LinkedVariable;
import lombok.*;
import lombok.experimental.*;
import org.lwjgl.opengl.*;

import java.nio.ByteBuffer;

import static com.basdxz.vbuffers.common.Constants.INT_SIZE;


@SuperBuilder
public abstract class GLSamplerBase<T extends LinkedVariable<T, Integer, Integer>> extends LinkedVariable<T, Integer, Integer> {
    @Override
    public Integer newDefaultOutput() {
        return 0;
    }

    @Override
    protected void setImpl(int byteOffset, @NonNull Integer input) {
        buffer.asIntBuffer().put(byteOffset / INT_SIZE, input);
    }

    @Override
    protected Integer getImpl(int byteOffset, @NonNull Integer output) {
        return buffer.asIntBuffer().get(byteOffset / INT_SIZE);
    }

    @Override
    protected void uploadUniformImpl(int program, int location, boolean transpose, @NonNull ByteBuffer input) {
        GL41.glProgramUniform1(program, location, input.asIntBuffer());
    }

    @Override
    protected void downloadUniformImpl(int program, int location, @NonNull ByteBuffer output) {
        GL20.glGetUniform(program, location, output.asIntBuffer());
    }
}
