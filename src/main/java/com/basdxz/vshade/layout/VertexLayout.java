package com.basdxz.vshade.layout;


import com.basdxz.vshade.variable.GLSLVariable;
import com.basdxz.vshade.variable.ILinkedVariable;
import lombok.*;
import lombok.experimental.*;

import java.util.Optional;

@Getter
@SuperBuilder
public abstract class VertexLayout extends VariableLayout {
    @Override
    public Optional<GLSLVariable> query(@NonNull String name) {
        return layoutProvider.shaderPeer().query().input(name);
    }

    @Override
    protected void handleLink(@NonNull ILinkedVariable<?, ?, ?> linkedVariable) {
        layoutProvider.shaderPeer().addVertexAttribute(linkedVariable);
    }
}
