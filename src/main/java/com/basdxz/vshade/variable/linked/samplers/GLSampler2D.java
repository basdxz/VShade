package com.basdxz.vshade.variable.linked.samplers;


import com.basdxz.vshade.type.GLSLType;
import com.basdxz.vshade.type.GLSLTypes;
import lombok.experimental.*;

@SuperBuilder
public class GLSampler2D extends GLSamplerBase<GLSampler2D> {
    @Override
    public GLSLType wrappedType() {
        return GLSLTypes.SAMPLER_2D;
    }
}