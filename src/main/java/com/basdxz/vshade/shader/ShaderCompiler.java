package com.basdxz.vshade.shader;

import com.basdxz.vshade.exception.ShaderException;
import lombok.*;
import org.lwjgl.opengl.*;

import java.util.ArrayList;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class ShaderCompiler {
    public static int createShader(Iterable<ShaderSource> sources) {
        val shaders = new ArrayList<Integer>();
        try {
            sources.forEach(source -> shaders.add(compileShader(source.source(), source.type().glEnum())));
            return createProgram(shaders);
        } finally {
            shaders.forEach(GL20::glDeleteShader);
        }
    }

    private static int compileShader(String source, int shaderType) {
        val shader = GL20.glCreateShader(shaderType);
        GL20.glShaderSource(shader, source);
        GL20.glCompileShader(shader);
        String infoString = GL20.glGetShaderInfoLog(shader, 2000);
        if (GL11.glGetError() != GL11.GL_NO_ERROR ||
                GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) != GL11.GL_TRUE || !infoString.equals("")) {
            GL20.glDeleteShader(shader);
            throw new ShaderException("Error while compiling shader: \n" + infoString);
        }
        return shader;
    }

    private static int createProgram(Iterable<Integer> shaders) {
        val program = GL20.glCreateProgram();
        for (val shader : shaders)
            GL20.glAttachShader(program, shader);

        GL20.glLinkProgram(program);
        verifyProgram(program, GL20.GL_LINK_STATUS, "Error while linking shader program");

        GL20.glValidateProgram(program);
        verifyProgram(program, GL20.GL_VALIDATE_STATUS, "Error while validating shader program");

        for (val shader : shaders)
            GL20.glDetachShader(program, shader);

        return program;
    }

    //TODO: Improve verification logging
    private static void verifyProgram(int program, int param, String message) {
        val infoLog = GL20.glGetProgramInfoLog(program, 2000);
        val status = GL20.glGetProgrami(program, param);
        val error = GL11.glGetError();

        if (error != GL11.GL_NO_ERROR || status != GL11.GL_TRUE) {
            GL20.glDeleteProgram(program);
            throw new ShaderException(message + ": \n" + (infoLog.equals("") ? "No Details Available" : infoLog));
        }

        if (!infoLog.equals(""))
            System.err.println(message + ": \n" + infoLog);
    }
}
