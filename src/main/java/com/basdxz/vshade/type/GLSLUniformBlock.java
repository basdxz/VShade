package com.basdxz.vshade.type;

import com.basdxz.vshade.variable.GLSLVariable;

import java.util.List;

public interface GLSLUniformBlock extends GLSLResource {
    List<GLSLVariable> variables();
}
