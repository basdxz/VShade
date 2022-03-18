package com.basdxz.vshade.example;

import lombok.*;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Quaternionf;
import org.joml.Vector3f;

@Getter
@Builder
public class ModelTransform {
    protected final Matrix4f model = new Matrix4f();
    @Builder.Default
    protected final Vector3f position = new Vector3f();
    @Builder.Default
    protected final Quaternionf rotation = new Quaternionf();
    @Builder.Default
    protected final Vector3f scale = new Vector3f(1.0F, 1.0F, 1.0F);

    public Matrix4fc model() {
        return new Matrix4f().translation(position).rotate(rotation).scale(scale);
    }
}
