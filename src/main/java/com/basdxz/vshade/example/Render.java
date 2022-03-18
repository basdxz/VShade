package com.basdxz.vshade.example;

import com.basdxz.vbuffers.common.Disposable;
import com.basdxz.vbuffers.texture.Texture;
import lombok.*;
import org.lwjgl.opengl.*;

@Getter
@Builder
public class Render implements Disposable {
    //    @NonNull
    protected VAOHandler vaoHandler;
    @NonNull
    protected final BasicShader shader;
    @NonNull
    protected final Camera camera;
    @Builder.Default
    protected final ModelTransform modelTransform = ModelTransform.builder().build();
    protected final Texture texture;

    @Builder.Default
    public boolean pogged = false;
    @Builder.Default
    public boolean pogged2 = true;
    public static int DATA_SIZE = 16;

    public void init() {
        vaoHandler = new VAOHandler(shader.shaderPeer());
    }

    public void doRender() {
//        if (!pogged) {
//            val uniformIndex = GL31.glGetUniformBlockIndex(shader.program(), "MatrixBlock");
//            GL31.glUniformBlockBinding(shader.program(), uniformIndex, 5);
//
//            // Create the UBO
//            val uboMatrices = GL15.glGenBuffers();
//            GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, uboMatrices);
//            GL15.glBufferData(GL31.GL_UNIFORM_BUFFER, DATA_SIZE, GL15.GL_STATIC_DRAW);
//            GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, GL11.GL_NONE);
//
//            GL30.glBindBufferBase(GL31.GL_UNIFORM_BUFFER, 5, uboMatrices);
//
//            // Fill the UBO
//            GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, uboMatrices);
//            val floatBuffer = MemUtils.newBufferFromValue(1.0F, 1.0F, 1.0F, 1.0F);
//
//            GL15.glBufferSubData(GL31.GL_UNIFORM_BUFFER, 0, floatBuffer);
//
//            GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, GL11.GL_NONE);
//            pogged = true;
//        }

        shader.uniforms().modelMatrix().set(modelTransform.model());
        shader.uniforms().viewMatrix().set(camera.view());
        shader.uniforms().projectionMatrix().set(camera.projection());

        vaoHandler.bind();

        if (texture != null)
            texture.bind(0);

        shader.bind();
        GL11.glDrawElements(GL11.GL_TRIANGLES, vaoHandler.indices(), GL11.GL_UNSIGNED_INT, GL11.GL_NONE);
        shader.unbind();

        Texture.unbind(0);
        vaoHandler.unbind();

    }

    @Override
    public void dispose() {
        vaoHandler.dispose();
        shader.dispose();
        if (texture != null)
            texture.dispose();
    }
}
