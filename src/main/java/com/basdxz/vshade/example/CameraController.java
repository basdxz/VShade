package com.basdxz.vshade.example;

import lombok.*;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;

import java.util.HashSet;
import java.util.Set;

public class CameraController {
    protected final Set<Integer> heldKeys = new HashSet<>();
    protected float movementFactor = 0.003F;
    protected float rotationFactor = 0.002F;

    public CameraController(Camera camera) {
        this.camera = camera;
    }

    protected final Camera camera;

    public void handleKey(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW.GLFW_PRESS && ! heldKeys.contains(key)) {
            heldKeys.add(key);
        } else {
            heldKeys.remove(key);
        }
    }

    public void update(int delta) {
        if (heldKeys.isEmpty())
            return;

        val movementDelta = movementFactor * delta;
        val rotationDelta = rotationFactor * delta;
        val rotation = new Quaternionf();
        camera.orientation.invert(rotation);

        if (heldKeys.contains(GLFW.GLFW_KEY_W))
            camera.position.add(new Vector3f(0, 0, 1).rotate(rotation).mul(movementDelta));
        if (heldKeys.contains(GLFW.GLFW_KEY_S))
            camera.position.add(new Vector3f(0, 0, -1).rotate(rotation).mul(movementDelta));

        if (heldKeys.contains(GLFW.GLFW_KEY_A))
            camera.position.add(new Vector3f(1, 0, 0).rotate(rotation).mul(movementDelta));
        if (heldKeys.contains(GLFW.GLFW_KEY_D))
            camera.position.add(new Vector3f(-1, 0, 0).rotate(rotation).mul(movementDelta));

        if (heldKeys.contains(GLFW.GLFW_KEY_SPACE))
            camera.position.add(new Vector3f(0, -1, 0).rotate(rotation).mul(movementDelta));
        if (heldKeys.contains(GLFW.GLFW_KEY_LEFT_CONTROL))
            camera.position.add(new Vector3f(0, 1, 0).rotate(rotation).mul(movementDelta));

        if (heldKeys.contains(GLFW.GLFW_KEY_LEFT))
            camera.orientation.rotateLocalY(-rotationDelta);
        if (heldKeys.contains(GLFW.GLFW_KEY_RIGHT))
            camera.orientation.rotateLocalY(rotationDelta);
        if (heldKeys.contains(GLFW.GLFW_KEY_UP))
            camera.orientation.rotateLocalX(-rotationDelta);
        if (heldKeys.contains(GLFW.GLFW_KEY_DOWN))
            camera.orientation.rotateLocalX(rotationDelta);
        if (heldKeys.contains(GLFW.GLFW_KEY_Q))
            camera.orientation.rotateLocalZ(-rotationDelta);
        if (heldKeys.contains(GLFW.GLFW_KEY_E))
            camera.orientation.rotateLocalZ(rotationDelta);

        if (heldKeys.contains(GLFW.GLFW_KEY_ESCAPE))
            System.exit(0);
    }
}
