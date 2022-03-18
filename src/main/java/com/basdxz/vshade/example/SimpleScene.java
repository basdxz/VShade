package com.basdxz.vshade.example;


import com.basdxz.vbuffers.common.Disposable;
import com.basdxz.vbuffers.common.ResourceHelper;
import com.basdxz.vbuffers.texture.Texture;
import com.basdxz.vshade.shader.ShaderSource;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.*;

public class SimpleScene implements Disposable {
    protected final int width;
    protected final int height;

    protected Camera camera = null;
    protected CameraController cameraController = null;
    protected Render render = null;

    public SimpleScene(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void reset() {
        setupCamera();
        setupRender();
        setupGeometry();
        setupGLFlags();
    }

    protected void setupCamera() {
        camera = new Camera(width, height);
        camera.updateProjection();
        cameraController = new CameraController(camera);
    }

    protected void setupRender() {
        render = Render.builder()
                .shader(BasicShader.builder()
                        .source(ShaderSource
                                .newVertex(ResourceHelper.readResourceAsString("/zerohero/shaders/basic.vert")))
                        .source(ShaderSource
                                .newFragment(ResourceHelper.readResourceAsString("/zerohero/shaders/basic.frag"))
                        ).name("Some Default Shader")
                        .build())
                .camera(camera)
                .texture(Texture.loadTexture("/rendercube/texture/blend1.png"))
                .modelTransform(ModelTransform.builder().position(new Vector3f(0, 0, -1)).build())
                .build();
    }

    protected void setupGeometry() {
//        render.vaoHandler().loadBuffers(Shapes.newFlatTriangle());
    }

    public void setupGLFlags() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_CULL_FACE);
    }

    public boolean isFinished() {
        return GLFW.glfwWindowShouldClose(ExampleMain.window);
    }

    public void update(Profiler profiler) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        render.doRender();
        profiler.updateMS();
    }

    @Override
    public void dispose() {
        render.dispose();
    }
}
