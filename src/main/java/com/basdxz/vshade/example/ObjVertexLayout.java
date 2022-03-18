package com.basdxz.vshade.example;


import com.basdxz.vshade.layout.VertexLayout;
import com.basdxz.vshade.variable.floats.GLFloatVec2;
import com.basdxz.vshade.variable.floats.GLFloatVec3;
import com.basdxz.vshade.variable.floats.GLFloatVec4;
import lombok.*;
import lombok.experimental.*;

@Getter
@SuperBuilder
public class ObjVertexLayout extends VertexLayout {
    protected final GLFloatVec3 position = GLFloatVec3.builder().variableLayout(this).name("in_Position").build().init();
    protected final GLFloatVec4 colour = GLFloatVec4.builder().variableLayout(this).name("in_Color").build().init();
    protected final GLFloatVec2 texture = GLFloatVec2.builder().variableLayout(this).name("in_TextureCoord").build().init();
}
