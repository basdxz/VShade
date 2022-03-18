package com.basdxz.vshade.query;

import com.basdxz.vshade.variable.GLSLVariable;

import java.util.Optional;

public interface IShaderQuery {
    Optional<GLSLVariable> input(String name);

    Optional<GLSLVariable> output(String name);

    Optional<GLSLVariable> uniform(String name);
}
