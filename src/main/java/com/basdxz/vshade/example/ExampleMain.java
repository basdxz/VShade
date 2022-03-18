package com.basdxz.vshade.example;

import lombok.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.*;

import java.awt.*;

public class ExampleMain {
    protected static String WINDOW_TITLE = "Basic Shader Demo";
    protected static int WINDOW_WIDTH = 800;
    protected static int WINDOW_HEIGHT = 600;
    protected static ContextAttribs OPENGL_CONTEXT_ATTRIBUTES = new ContextAttribs(4, 5)
            .withForwardCompatible(true)
            .withProfileCore(true);
    protected static Color BACKGROUND_COLOR = new Color(103, 154, 230);
    protected static boolean VSYNC = false;
    protected static int FPS_CAP = 0;

    protected static Profiler profiler = new Profiler();
    protected static float timeTillTitleUpdate = 0F;

    @SneakyThrows
    public static void main(String[] args) {
        initOpenGL();
        initOpenGLDebug();
        val scene = new ModelScene(WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.reset();

        while (!scene.isFinished()) {
            scene.update(profiler);
            Display.update();
            Display.sync(FPS_CAP);

            profiler.updateTime();
            updateWindowTitle();
        }
    }

    protected static void initOpenGL() {
        NativeLoader.load();
        try {
            Display.setDisplayMode(new DisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT));
            Display.create(new PixelFormat(), OPENGL_CONTEXT_ATTRIBUTES);
            Display.setVSyncEnabled(VSYNC);
            GL11.glClearColor(
                    BACKGROUND_COLOR.getRed() / 255F,
                    BACKGROUND_COLOR.getGreen() / 255F,
                    BACKGROUND_COLOR.getBlue() / 255F,
                    0
            );
            GL11.glViewport(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    protected static void updateWindowTitle() {
        timeTillTitleUpdate -= profiler.updateMS();
        if (timeTillTitleUpdate <= 0) {
            Display.setTitle(String.format("%s: %d fps %.2f ms", WINDOW_TITLE, profiler.updatesPerSecond(), profiler.updateMS()));
            timeTillTitleUpdate = 2000;
        }
    }

    protected static void initOpenGLDebug() {
        GL11.glEnable(GL43.GL_DEBUG_OUTPUT);
        GL11.glEnable(GL43.GL_DEBUG_OUTPUT_SYNCHRONOUS);
        GL43.glDebugMessageCallback(new KHRDebugCallback());
    }
}
