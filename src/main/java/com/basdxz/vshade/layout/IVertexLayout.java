package com.basdxz.vshade.layout;

import com.basdxz.vshade.variable.linked.ILinkedVariable;


public interface IVertexLayout extends IVariableLayout {
    Iterable<ILinkedVariable<?, ?, ?>> linkedAttributes();
}
