package com.basdxz.vshade.layout;


import com.basdxz.vbuffers.common.MemUtils;
import com.basdxz.vshade.variable.GLSLVariable;
import com.basdxz.vshade.variable.ILinkedVariable;
import lombok.*;
import lombok.experimental.*;

import java.util.Optional;

@Getter
@SuperBuilder
public abstract class UniformLayout extends VariableLayout {
    @Override
    public Optional<GLSLVariable> query(@NonNull String name) {
        return layoutProvider.shaderPeer().query().uniform(name);
    }

    @Override
    protected void handleLink(@NonNull ILinkedVariable<?, ?, ?> linkedVariable) {
        linkedVariable.blocks(1).legacyUniform(true).buffer(MemUtils.newByteBuffer(linkedVariable.typeSize()));
    }
}
