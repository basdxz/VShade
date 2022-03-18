package com.basdxz.vshade.shader;

import lombok.*;
import org.lwjgl.opengl.*;

@Getter
@AllArgsConstructor
public class ShaderSource {
    protected final String source;
    protected final Type type;

    public static ShaderSource newVertex(String source) {
        return new ShaderSource(source, Type.VERTEX);
    }

    public static ShaderSource newFragment(String source) {
        return new ShaderSource(source, Type.FRAGMENT);
    }

    @Getter
    @AllArgsConstructor
    public enum Type {
        VERTEX(GL20.GL_VERTEX_SHADER), GEOMETRY(GL32.GL_GEOMETRY_SHADER), FRAGMENT(GL20.GL_FRAGMENT_SHADER);

        private final int glEnum;
    }
}
