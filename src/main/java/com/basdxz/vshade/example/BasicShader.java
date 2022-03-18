package com.basdxz.vshade.example;


import com.basdxz.vshade.shader.ShaderProgram;
import lombok.*;
import lombok.experimental.*;

@Getter
@SuperBuilder
public class BasicShader extends ShaderProgram {
    protected final MVPUniforms uniforms = MVPUniforms.builder().layoutProvider(this).build().init();
    protected final ObjVertexLayout attributes = ObjVertexLayout.builder().layoutProvider(this).build().init();
}
