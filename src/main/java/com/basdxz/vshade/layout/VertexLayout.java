package com.basdxz.vshade.layout;


import com.basdxz.vshade.variable.GLSLVariable;
import com.basdxz.vshade.variable.linked.ILinkedVariable;
import lombok.*;
import lombok.experimental.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@SuperBuilder
public abstract class VertexLayout extends VariableLayout implements IVertexLayout {
    @Getter
    protected final Set<ILinkedVariable<?, ?, ?>> linkedAttributes = new HashSet<>();

    @Override
    public Optional<GLSLVariable> query(@NonNull String name) {
        return layoutProvider.shaderPeer().query().input(name);
    }

    @Override
    protected void handleLink(@NonNull ILinkedVariable<?, ?, ?> linkedVariable) {
        linkedAttributes.add(linkedVariable);
    }

    @Override
    public void dispose() {
        if (linked)
            linkedAttributes.clear();
        super.dispose();
    }
}
