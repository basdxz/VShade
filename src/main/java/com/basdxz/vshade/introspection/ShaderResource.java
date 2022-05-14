package com.basdxz.vshade.introspection;

import lombok.*;

import java.util.Map;
import java.util.Optional;

import static lombok.AccessLevel.NONE;

@Getter
@AllArgsConstructor
public class ShaderResource implements IShaderResource {
    @NonNull
    protected final String name;
    @NonNull
    protected final IShaderInterface shaderInterface;
    @Getter(NONE)
    @NonNull
    protected final Map<IResourceProperty, IResourceParameter> parameters;

    @Override
    public Optional<IResourceParameter> parameter(@NonNull IResourceProperty type) {
        return Optional.ofNullable(parameters.get(type));
    }
}
