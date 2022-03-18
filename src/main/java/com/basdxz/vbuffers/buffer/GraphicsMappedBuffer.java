package com.basdxz.vbuffers.buffer;

import com.basdxz.vbuffers.common.Disposable;
import com.basdxz.vbuffers.common.MemUtils;
import lombok.*;
import org.lwjgl.opengl.*;

import java.nio.ByteBuffer;

/*
    TODO: Design better abstraction, make buffers match types structure-wise. Perhaps have purpose-made buffers with sane defaults?
 */
@Getter
public class GraphicsMappedBuffer implements Disposable {
    //NOTE: Coherent bit doesn't hurt performance anywhere near as much as read bit does.
    //protected static final int FLAGS = GL30.GL_MAP_WRITE_BIT | GL30.GL_MAP_READ_BIT | GL44.GL_MAP_PERSISTENT_BIT | GL44.GL_MAP_COHERENT_BIT;
    protected static final int FLAGS = GL30.GL_MAP_WRITE_BIT | GL44.GL_MAP_PERSISTENT_BIT | GL44.GL_MAP_COHERENT_BIT;

    protected final Type type;
    protected final int bufferID;
    protected final ByteBuffer buffer;

    //TODO: Improve error thing idk AND it will still crash if there are no attributes, which should honestly just make some blank shit I guess? Fail safer.
    public GraphicsMappedBuffer(@NonNull Type type, int byteSize) {
        if (byteSize <= 0)
            throw new IllegalArgumentException("byteSize <= 0");
        this.type = type;
        bufferID = GL45.glCreateBuffers();
        val tempBuffer = MemUtils.newByteBuffer(byteSize);
        GL45.glNamedBufferStorage(bufferID, tempBuffer, FLAGS);
        buffer = GL45.glMapNamedBufferRange(bufferID, 0, byteSize, FLAGS, tempBuffer);
    }

    public static GraphicsMappedBuffer newArrayBuffer(int byteSize) {
        return new GraphicsMappedBuffer(Type.ARRAY, byteSize);
    }

    public static GraphicsMappedBuffer newElementArrayBuffer(int byteSize) {
        return new GraphicsMappedBuffer(Type.ELEMENT_ARRAY, byteSize);
    }

    public void syncUpload() {
        GL42.glMemoryBarrier(GL44.GL_CLIENT_MAPPED_BUFFER_BARRIER_BIT);
    }

    public void syncDownload() {
        GL42.glMemoryBarrier(GL44.GL_CLIENT_MAPPED_BUFFER_BARRIER_BIT);
        GL32.glFenceSync(GL32.GL_SYNC_GPU_COMMANDS_COMPLETE, 0);
    }

    public void bind() {
        GL15.glBindBuffer(type.glEnum(), bufferID);
    }

    public void unbind() {
        GL15.glBindBuffer(type.glEnum(), GL11.GL_NONE);
    }

    @Override
    public void dispose() {
        GL15.glDeleteBuffers(bufferID);
    }

    @Getter
    @AllArgsConstructor
    public enum Type {
        ARRAY(GL15.GL_ARRAY_BUFFER), ELEMENT_ARRAY(GL15.GL_ELEMENT_ARRAY_BUFFER);

        private final int glEnum;
    }
}
