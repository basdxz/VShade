package com.basdxz.vshade.layout;


import com.basdxz.vbuffers.common.Disposable;
import com.basdxz.vshade.variable.GLSLVariable;
import com.basdxz.vshade.variable.linked.ILinkedVariable;
import lombok.*;

import java.util.Optional;

public interface IVariableLayout extends Disposable {
    void preLink(@NonNull ILinkedVariable<?, ?, ?> linkedVariable);

    void link();

    Optional<GLSLVariable> query(@NonNull String name);
}
