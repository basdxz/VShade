package com.basdxz.vshade.introspection;

import java.nio.IntBuffer;
import java.util.List;

public interface IShaderInterface {
    int interfaceType();

    List<IResourceProperty> parameterTypes();

    IntBuffer props();
}
