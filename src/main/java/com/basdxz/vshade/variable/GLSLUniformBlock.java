package com.basdxz.vshade.variable;

import com.basdxz.vshade.type.GLSLResource;

import java.util.List;

public interface GLSLUniformBlock extends GLSLResource {
    List<GLSLVariable> variables();
}
