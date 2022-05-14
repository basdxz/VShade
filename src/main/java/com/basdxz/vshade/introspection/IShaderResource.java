package com.basdxz.vshade.introspection;

import lombok.*;

import java.util.Optional;

public interface IShaderResource {
    String name();

    IShaderInterface shaderInterface();

    Optional<IResourceParameter> parameter(@NonNull IResourceProperty type);
}
