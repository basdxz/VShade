package com.basdxz.vshade.introspection;

import lombok.*;

@Getter
@AllArgsConstructor
public class ResourceParameter implements IResourceParameter {
    @NonNull
    protected final IResourceProperty property;
    protected final int value;
}
