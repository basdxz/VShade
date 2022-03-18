package com.basdxz.vshade.example;

import lombok.*;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.*;

import java.awt.*;

public class ExampleMain {
    protected static String WINDOW_TITLE = "Basic Shader Demo";
    protected static int WINDOW_WIDTH = 800;
    protected static int WINDOW_HEIGHT = 600;
//    protected static ContextAttribs OPENGL_CONTEXT_ATTRIBUTES = new ContextAttribs(4, 5)
//            .withForwardCompatible(true)
//            .withProfileCore(true);
    protected static Color BACKGROUND_COLOR = new Color(103, 154, 230);
    protected static boolean VSYNC = false;
    protected static int FPS_CAP = 0;
    public static long window;

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
            GLFW.glfwPollEvents();
            GLFW.glfwSwapBuffers(window);

            profiler.updateTime();
            updateWindowTitle();
        }
    }

    protected static void initOpenGL() {
        //NativeLoader.loadLWJGL();
        GLFW.glfwInit();
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_COMPAT_PROFILE);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLFW.GLFW_TRUE);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 4);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 5);
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_TRUE);
        window = GLFW.glfwCreateWindow(WINDOW_WIDTH, WINDOW_HEIGHT, WINDOW_TITLE, 0, 0);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        GLFW.glfwSwapInterval(VSYNC ? 1 : 0);
        GL11.glClearColor(
                BACKGROUND_COLOR.getRed() / 255F,
                BACKGROUND_COLOR.getGreen() / 255F,
                BACKGROUND_COLOR.getBlue() / 255F,
                0
        );
        GL11.glViewport(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    protected static void updateWindowTitle() {
        timeTillTitleUpdate -= profiler.updateMS();
        if (timeTillTitleUpdate <= 0) {
            GLFW.glfwSetWindowTitle(window, String.format("%s: %d fps %.2f ms", WINDOW_TITLE, profiler.updatesPerSecond(), profiler.updateMS()));
            timeTillTitleUpdate = 2000;
        }
    }

    protected static void initOpenGLDebug() {
        GL11.glEnable(GL43.GL_DEBUG_OUTPUT);
        GL11.glEnable(GL43.GL_DEBUG_OUTPUT_SYNCHRONOUS);
        //TODO GL43.glDebugMessageCallback(new KHRDebugCallback());
    }
}
