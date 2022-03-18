package com.basdxz.vshade.example;


import com.basdxz.vshade.layout.UniformLayout;
import com.basdxz.vshade.variable.linked.floats.GLFloat;
import com.basdxz.vshade.variable.linked.floats.GLFloatVec3;
import com.basdxz.vshade.variable.linked.floats.GLFloatVec4;
import com.basdxz.vshade.variable.linked.ints.GLInt;
import com.basdxz.vshade.variable.linked.samplers.GLSampler2D;
import lombok.*;
import lombok.experimental.*;

@Getter
@SuperBuilder
public class ShaderToyUniforms extends UniformLayout {
    protected final GLFloatVec3 iResolution = GLFloatVec3.builder().variableLayout(this).name("iResolution").build().init();
    protected final GLFloat iTime = GLFloat.builder().variableLayout(this).name("iTime").build().init();
    protected final GLFloat iTimeDelta = GLFloat.builder().variableLayout(this).name("iTimeDelta").build().init();
    protected final GLInt iFrame = GLInt.builder().variableLayout(this).name("iFrame").build().init();
    protected final GLFloat iChannelTime = GLFloat.builder().variableLayout(this).name("iChannelTime").arraySize(4).build().init();
    protected final GLFloatVec3 iChannelResolution = GLFloatVec3.builder().variableLayout(this).name("iChannelResolution").arraySize(4).build().init();
    protected final GLFloatVec4 iMouse = GLFloatVec4.builder().variableLayout(this).name("iMouse").build().init();
    // These can also be a cube map I guess ?
    protected final GLSampler2D iChannel0 = GLSampler2D.builder().variableLayout(this).name("iChannel0").build().init();
    protected final GLSampler2D iChannel1 = GLSampler2D.builder().variableLayout(this).name("iChannel1").build().init();
    protected final GLSampler2D iChannel2 = GLSampler2D.builder().variableLayout(this).name("iChannel2").build().init();
    protected final GLSampler2D iChannel3 = GLSampler2D.builder().variableLayout(this).name("iChannel3").build().init();
    protected final GLFloatVec4 iDate = GLFloatVec4.builder().variableLayout(this).name("iDate").build().init();
    protected final GLFloat iSampleRate = GLFloat.builder().variableLayout(this).name("iSampleRate").build().init();
}
