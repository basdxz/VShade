package com.basdxz.vshade.layout;


import com.basdxz.vshade.variable.GLSLVariable;
import com.basdxz.vshade.variable.ILinkedVariable;
import lombok.*;

import java.util.Optional;

public interface IVariableLayout {
    void preLink(@NonNull ILinkedVariable<?, ?, ?> linkedVariable);

    void link();

    Optional<GLSLVariable> query(@NonNull String name);
}
