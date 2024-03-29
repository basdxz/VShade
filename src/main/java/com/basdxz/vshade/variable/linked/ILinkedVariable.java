package com.basdxz.vshade.variable.linked;


import com.basdxz.vbuffers.common.MemUtils;
import com.basdxz.vshade.type.SelfTyped;
import com.basdxz.vshade.variable.GLSLVariableLink;
import lombok.*;

import java.nio.*;

public interface ILinkedVariable<T extends ILinkedVariable<T, INPUT, OUTPUT>, INPUT, OUTPUT> extends GLSLVariableLink, SelfTyped<T> {
    T autoUpdate(boolean autoUpdate);

    T transpose(boolean transpose);

    T legacyUniform(boolean legacyUniform);

    T offset(int offset);

    T blockStride(int blockStride);

    T blocks(int blocks);

    T buffer(ByteBuffer buffer);

    T disposableBuffer(boolean disposableBuffer);

    default T set(@NonNull final INPUT... inputs) {
        return set(0, inputs);
    }

    T set(int block, @NonNull final INPUT... inputs);

    default T set(@NonNull final INPUT input) {
        return set(0, 0, input);
    }

    default T set(int index, @NonNull final INPUT input) {
        return set(0, index, input);
    }

    T set(int block, int index, @NonNull final INPUT input);

    default T set(@NonNull final CharBuffer input) {
        return set(0, input);
    }

    default T set(@NonNull final ShortBuffer input) {
        return set(0, input);
    }

    default T set(@NonNull final IntBuffer input) {
        return set(0, input);
    }

    default T set(@NonNull final LongBuffer input) {
        return set(0, input);
    }

    default T set(@NonNull final FloatBuffer input) {
        return set(0, input);
    }

    default T set(@NonNull final DoubleBuffer input) {
        return set(0, input);
    }

    default T set(@NonNull final ByteBuffer input) {
        return set(0, input);
    }

    default T set(int blockOffset, @NonNull final CharBuffer input) {
        return set(blockOffset, MemUtils.getByteBuffer(input));
    }

    default T set(int blockOffset, @NonNull final ShortBuffer input) {
        return set(blockOffset, MemUtils.getByteBuffer(input));
    }

    default T set(int blockOffset, @NonNull final IntBuffer input) {
        return set(blockOffset, MemUtils.getByteBuffer(input));
    }

    default T set(int blockOffset, @NonNull final LongBuffer input) {
        return set(blockOffset, MemUtils.getByteBuffer(input));
    }

    default T set(int blockOffset, @NonNull final FloatBuffer input) {
        return set(blockOffset, MemUtils.getByteBuffer(input));
    }

    default T set(int blockOffset, @NonNull final DoubleBuffer input) {
        return set(blockOffset, MemUtils.getByteBuffer(input));
    }

    T set(int blockOffset, @NonNull final ByteBuffer input);

    void upload();

    OUTPUT newDefaultOutput();

    default OUTPUT get() {
        return get(0, 0, newDefaultOutput());
    }

    default OUTPUT[] get(@NonNull final OUTPUT... outputs) {
        return get(0, outputs);
    }

    OUTPUT[] get(int block, @NonNull final OUTPUT... outputs);

    default OUTPUT get(@NonNull final OUTPUT output) {
        return get(0, 0, output);
    }

    default OUTPUT get(int index) {
        return get(0, index, newDefaultOutput());
    }

    default OUTPUT get(int index, @NonNull final OUTPUT output) {
        return get(0, index, output);
    }

    default OUTPUT get(int block, int index) {
        return get(block, index, newDefaultOutput());
    }

    OUTPUT get(int block, int index, @NonNull final OUTPUT output);

    default CharBuffer get(@NonNull final CharBuffer output) {
        return get(0, output);
    }

    default ShortBuffer get(@NonNull final ShortBuffer output) {
        return get(0, output);
    }

    default IntBuffer get(@NonNull final IntBuffer output) {
        return get(0, output);
    }

    default LongBuffer get(@NonNull final LongBuffer output) {
        return get(0, output);
    }

    default FloatBuffer get(@NonNull final FloatBuffer output) {
        return get(0, output);
    }

    default DoubleBuffer get(@NonNull final DoubleBuffer output) {
        return get(0, output);
    }

    default ByteBuffer get(@NonNull final ByteBuffer output) {
        return get(0, output);
    }

    default CharBuffer get(int blockOffset, @NonNull final CharBuffer output) {
        get(blockOffset, MemUtils.getByteBuffer(output));
        return output;
    }

    default ShortBuffer get(int blockOffset, @NonNull final ShortBuffer output) {
        get(blockOffset, MemUtils.getByteBuffer(output));
        return output;
    }

    default IntBuffer get(int blockOffset, @NonNull final IntBuffer output) {
        get(blockOffset, MemUtils.getByteBuffer(output));
        return output;
    }

    default LongBuffer get(int blockOffset, @NonNull final LongBuffer output) {
        get(blockOffset, MemUtils.getByteBuffer(output));
        return output;
    }

    default FloatBuffer get(int blockOffset, @NonNull final FloatBuffer output) {
        get(blockOffset, MemUtils.getByteBuffer(output));
        return output;
    }

    default DoubleBuffer get(int blockOffset, @NonNull final DoubleBuffer output) {
        get(blockOffset, MemUtils.getByteBuffer(output));
        return output;
    }

    ByteBuffer get(int blockOffset, @NonNull final ByteBuffer output);

    void download();
}
