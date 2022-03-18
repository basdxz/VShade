package com.basdxz.vshade.layout;


import com.basdxz.vshade.query.ShaderQuery;

public interface LayoutQueryProvider {
    void link(IVariableLayout layout);

    ShaderQuery query();
}
