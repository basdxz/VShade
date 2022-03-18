package com.basdxz.vshade.example;

import lombok.*;
import lombok.experimental.*;

@Getter
@SuperBuilder
public class ShaderToy extends BasicShader {
    protected final ShaderToyUniforms shaderToyUniforms = ShaderToyUniforms.builder().layoutProvider(this).build().init();
}
