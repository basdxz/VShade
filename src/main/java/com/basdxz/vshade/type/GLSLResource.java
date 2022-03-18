package com.basdxz.vshade.type;

public interface GLSLResource {
    String name();

    int program();

    int location();

    int offset();

    int stride();

    int arraySize();

    int arrayStride();
}
