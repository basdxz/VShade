package com.basdxz.vshade.layout;


import com.basdxz.vshade.shader.ShaderLinkProvider;
import com.basdxz.vshade.variable.GLSLVariableLink;
import com.basdxz.vshade.variable.ILinkedVariable;
import lombok.*;
import lombok.experimental.*;

import java.util.HashSet;
import java.util.Set;

@SuperBuilder
public abstract class VariableLayout implements IVariableLayout {
    protected final Set<ILinkedVariable<?, ?, ?>> variables = new HashSet<>();
    @NonNull
    protected ShaderLinkProvider layoutProvider;

    protected boolean linked;
    protected boolean postLinked;
    protected int blockStride;

    public <R extends VariableLayout> R init() {
        layoutProvider.shaderPeer().addLayout(this);
        return (R) this;
    }

    @Override
    public void preLink(@NonNull ILinkedVariable<?, ?, ?> linkedVariable) {
        if (!linked)
            variables.add(linkedVariable);
    }

    @Override
    public void link() {
        if (!linked) {
            variables.stream().filter(GLSLVariableLink::link).forEach(this::handleLink);
            linked = true;
        }
    }

    protected abstract void handleLink(@NonNull ILinkedVariable<?, ?, ?> linkedVariable);
}
