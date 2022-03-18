package com.basdxz.vshade.example;

import lombok.*;
import lombok.experimental.*;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

@Getter
@Accessors(fluent = true)
public class Camera {
    protected final Matrix4f projection = new Matrix4f();
    protected final Matrix4f view = new Matrix4f();
    protected final Vector3f position = new Vector3f();
    protected final Quaternionf orientation = new Quaternionf();

    protected float fov;
    protected float width;
    protected float height;
    protected float aspect;
    protected float zNear;
    protected float zFar;

    public Camera(float width, float height) {
        this.width = width;
        this.height = height;
        aspect = width / height;
        fov = 60;
        zNear = 0.1f;
        zFar = 100f;

        //orientation.rotateY((float) Math.toRadians(15));
        position.z = -10;
    }

    public Camera updateProjection() {
        projection.perspective((float) Math.toRadians(fov), width / height, 0.1f, 100f);
        return this;
    }

    public Camera updateView() {
        view.identity().rotate(orientation).translate(position);
        return this;
    }
}
