package com.basdxz.vbuffers.texture;

import com.basdxz.vbuffers.common.Disposable;
import de.matthiasmann.twl.utils.PNGDecoder;
import lombok.*;
import lombok.experimental.*;
import net.buttology.lwjgl.dds.DDSFile;
import org.lwjgl.opengl.*;

import java.nio.ByteBuffer;

@Accessors(fluent = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Texture implements Disposable {
    protected static final int BIT_PER_FRAG = 4;

    protected final int texID;

    @SneakyThrows
    public static Texture loadCompressedTexture(String fileName) {
        DDSFile file = new DDSFile(Texture.class.getResourceAsStream(fileName));
        int textureID = GL11.glGenTextures();       // Generate a texture ID.
        GL13.glActiveTexture(GL13.GL_TEXTURE0);     // Depends on your implementation
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
        for (int level = 0; level < file.getMipMapCount(); level++)
            GL13.glCompressedTexImage2D(
                    GL11.GL_TEXTURE_2D,
                    level,
                    file.getFormat(),
                    file.getWidth(level),
                    file.getHeight(level),
                    0,
                    file.getBuffer(level)
            );
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL12.GL_TEXTURE_BASE_LEVEL, 0);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL12.GL_TEXTURE_MAX_LEVEL, file.getMipMapCount() - 1);
        return new Texture(textureID);
    }

    @SneakyThrows
    public static Texture loadTexture(String fileName) {
        // Get and decode texture from memory
        val decoder = new PNGDecoder(Texture.class.getResourceAsStream(fileName));
        val buffer = ByteBuffer.allocateDirect(BIT_PER_FRAG * decoder.getWidth() * decoder.getHeight());
        decoder.decode(buffer, decoder.getWidth() * BIT_PER_FRAG, PNGDecoder.Format.RGBA);
        buffer.flip();

        // Allocate new texture id
        val id = GL45.glCreateTextures(GL11.GL_TEXTURE_2D);

        // Set scaling flags
        GL45.glTextureParameteri(id, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
        GL45.glTextureParameteri(id, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);

        // Set format and upload texture
        GL45.glTextureStorage2D(id, 4, GL11.GL_RGBA8, decoder.getWidth(), decoder.getHeight());
        GL45.glTextureSubImage2D(id, 0, 0, 0, decoder.getWidth(), decoder.getHeight(), GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);

        // Gen mipmaps
        GL45.glGenerateTextureMipmap(id);

        return new Texture(id);
    }

    public void bind(int texUnit) {
        GL45.glBindTextureUnit(texUnit, texID);
    }

    public static void unbind(int texUnit) {
        GL45.glBindTextureUnit(texUnit, 0);
    }

    @Override
    public void dispose() {
        GL11.glDeleteTextures(texID);
    }
}
