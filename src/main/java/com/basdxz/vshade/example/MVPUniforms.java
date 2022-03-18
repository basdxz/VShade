package com.basdxz.vshade.example;


import com.basdxz.vshade.layout.UniformLayout;
import com.basdxz.vshade.variable.linked.floats.GLFloatMat4;
import lombok.*;
import lombok.experimental.*;

@Getter
@SuperBuilder
public class MVPUniforms extends UniformLayout {
    protected final GLFloatMat4 modelMatrix = GLFloatMat4.builder().variableLayout(this).name("modelMatrix").build().init();
    protected final GLFloatMat4 viewMatrix = GLFloatMat4.builder().variableLayout(this).name("viewMatrix").build().init();
    protected final GLFloatMat4 projectionMatrix = GLFloatMat4.builder().variableLayout(this).name("projectionMatrix").build().init();
}
