package com.basdxz.vshade.shader;

import com.basdxz.vbuffers.common.Disposable;
import lombok.*;
import lombok.experimental.*;
import org.lwjgl.opengl.*;

import java.util.Set;

@Getter
@SuperBuilder
public abstract class ShaderProgram implements ShaderLinkProvider, Disposable {
    protected static int currentShader = 0;

    protected final ShaderPeer shaderPeer = new ShaderPeer();
    @NonNull
    protected final String name;
    @Singular
    @NonNull
    protected final Set<ShaderSource> sources;

    protected boolean isCompiled;
    protected int program;

    public <T extends ShaderProgram> T init() {
        if (!isCompiled) {
            program = ShaderCompiler.createShader(sources);
            shaderPeer.init(program);
            isCompiled = true;
            shaderPeer.link();
        }
        return (T) this;
    }

    public void bind() {
        if (!isCompiled || currentShader == program)
            return;
        currentShader = program;
        GL20.glUseProgram(program);
    }

    @Override
    public void dispose() {
        unbind();
        shaderPeer.dispose();
        isCompiled = false;
        GL20.glDeleteProgram(program);
    }

    public void unbind() {
        if (currentShader == 0)
            return;
        currentShader = 0;
        GL20.glUseProgram(0);
    }
}
