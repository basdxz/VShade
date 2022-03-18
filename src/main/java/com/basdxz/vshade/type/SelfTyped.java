package com.basdxz.vshade.type;

public interface SelfTyped<T extends SelfTyped<T>> {

    @SuppressWarnings("unchecked")
    default T self() {
        return (T) this;
    }
}
