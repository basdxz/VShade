package com.basdxz.vshade.variable.linked;


import com.basdxz.vshade.exception.ShaderException;
import com.basdxz.vshade.layout.IVariableLayout;
import com.basdxz.vshade.variable.GLSLVariable;
import lombok.*;
import lombok.var;
import lombok.experimental.*;

import java.nio.ByteBuffer;

//TODO: Ensure buffers are properly disposed of
//TODO: Verbose logging for linking and disposal
@SuperBuilder
public abstract class LinkedVariable<T extends LinkedVariable<T, INPUT, OUTPUT>, INPUT, OUTPUT> implements ILinkedVariable<T, INPUT, OUTPUT> {
    @Getter
    @NonNull
    protected final String name;
    @Getter
    @Builder.Default
    protected final int arraySize = 1;
    @NonNull
    protected final IVariableLayout variableLayout;

    @Getter
    protected boolean linked;
    @Getter
    protected GLSLVariable variable;

    @Builder.Default
    protected boolean autoUpdate = true;
    protected boolean transpose;
    protected boolean legacyUniform;
    protected int offset;
    protected int blockStride;
    protected int blocks;
    protected ByteBuffer buffer;
    protected boolean disposableBuffer;

    public T init() {
        variableLayout.preLink(this);
        return self();
    }

    @Override
    public boolean link() {
        if (!linked) {
            variableLayout.query(name).ifPresent(queryVariable -> {
                if (!variableEqual(queryVariable))
                    return;
                variable = queryVariable;
                linked = true;
            });
        }
        return linked;
    }

    @Override
    public T autoUpdate(boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
        return self();
    }

    @Override
    public T transpose(boolean transpose) {
        this.transpose = transpose;
        return self();
    }

    @Override
    public T legacyUniform(boolean legacyUniform) {
        this.legacyUniform = legacyUniform;
        return self();
    }

    @Override
    public T offset(int offset) {
        this.offset = offset;
        return self();
    }

    @Override
    public T blockStride(int blockStride) {
        this.blockStride = blockStride;
        return self();
    }

    @Override
    public T blocks(int blocks) {
        this.blocks = blocks;
        return self();
    }

    @Override
    public T buffer(ByteBuffer buffer) {
        this.buffer = buffer;
        return self();
    }

    @Override
    public T disposableBuffer(boolean disposableBuffer) {
        this.disposableBuffer = disposableBuffer;
        return self();
    }

    @Override
    public T set(int block, @NonNull INPUT... inputs) {
        if (linked) {
            blockIndexCheck(block);
            arrayIndexCheck(inputs.length - 1);
            for (int i = 0; i < inputs.length; i++)
                setImpl(byteOffset(block, i), inputs[i]);
            autoUpload();
        }
        return self();
    }

    @Override
    public T set(int block, int index, @NonNull INPUT input) {
        if (linked) {
            blockIndexCheck(block);
            arrayIndexCheck(index);
            setImpl(byteOffset(block, index), input);
            autoUpload();
        }
        return self();
    }

    protected abstract void setImpl(int byteOffset, @NonNull INPUT input);

    @Override
    public T set(int blockOffset, @NonNull ByteBuffer input) {
        if (linked) {
            blockIndexCheck(blockOffset);
            val blockLimit = Math.min(input.remaining() / typeSize(), blocks);
            var bufferOffset = offset();
            for (var i = blockOffset; i < blockLimit; i++) {
                for (var j = 0; j < typeSize(); j++)
                    buffer.put(bufferOffset + j, input.get());
                bufferOffset += blockStride;
            }
            autoUpload();
        }
        return self();
    }

    protected void autoUpload() {
        if (autoUpdate)
            upload();
    }

    @Override
    public void upload() {
        if (linked && legacyUniform)
            uploadUniformImpl(variable.program(), variable.location(), transpose, buffer);
    }

    protected abstract void uploadUniformImpl(int program, int location, boolean transpose, @NonNull ByteBuffer input);

    @Override
    public OUTPUT[] get(int block, @NonNull OUTPUT... outputs) {
        if (linked) {
            blockIndexCheck(block);
            arrayIndexCheck(outputs.length - 1);
            autoDownload();
            for (int i = 0; i < outputs.length; i++)
                outputs[i] = getImpl(byteOffset(block, i), outputs[i]);
        }
        return outputs;
    }

    @Override
    public OUTPUT get(int block, int index, @NonNull OUTPUT output) {
        if (linked) {
            blockIndexCheck(block);
            arrayIndexCheck(index);
            autoDownload();
            return getImpl(byteOffset(block, index), output);
        }
        return output;
    }

    protected abstract OUTPUT getImpl(int byteOffset, @NonNull OUTPUT output);

    protected int byteOffset(int block, int index) {
        return (block * blockStride) + (index * arrayStride());
    }

    @Override
    public ByteBuffer get(int blockOffset, @NonNull ByteBuffer output) {
        if (!linked) {
            blockIndexCheck(blockOffset);
            autoDownload();
            var blockLimit = Math.min(output.remaining() / typeSize(), blocks);
            var bufferOffset = offset();
            for (var i = blockOffset; i < blockLimit; i++) {
                for (var j = 0; j < typeSize(); j++)
                    output.put(buffer.get(bufferOffset + j));
                bufferOffset += blockStride;
            }
        }
        return output;
    }

    public void blockIndexCheck(int block) {
        if (block >= blocks)
            throw new ShaderException("Block index out of bounds, block count is %d but tried to access %d.", blocks, block);
    }

    public void arrayIndexCheck(int index) {
        if (index >= arraySize)
            throw new ShaderException("Array index out of bounds, array size is %d but tried to access %d.", arraySize, index);
    }

    protected void autoDownload() {
        if (autoUpdate)
            download();
    }

    @Override
    public void download() {
        if (linked && legacyUniform)
            downloadUniformImpl(variable.program(), variable.location(), buffer);
    }

    protected abstract void downloadUniformImpl(int program, int location, @NonNull ByteBuffer output);

    @Override
    public void dispose() {
        if (linked) {
            variable = null;
            linked = false;
            buffer = null;
        }
    }
}
