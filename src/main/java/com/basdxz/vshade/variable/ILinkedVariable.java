package com.basdxz.vshade.variable;


import com.basdxz.vbuffers.common.MemUtils;
import lombok.*;

import java.nio.*;

public interface ILinkedVariable<T extends ILinkedVariable<T, INPUT, OUTPUT>, INPUT, OUTPUT> extends GLSLVariableLink {
    <R extends T> R autoUpdate(boolean autoUpdate);

    <R extends T> R transpose(boolean transpose);

    <R extends T> R legacyUniform(boolean legacyUniform);

    <R extends T> R offset(int offset);

    <R extends T> R blockStride(int blockStride);

    <R extends T> R blocks(int blocks);

    <R extends T> R buffer(ByteBuffer buffer);

    <R extends T> R disposableBuffer(boolean disposableBuffer);

    default <R extends T> R set(@NonNull final INPUT... inputs) {
        return set(0, inputs);
    }

    <R extends T> R set(int block, @NonNull final INPUT... inputs);

    default <R extends T> R set(@NonNull final INPUT input) {
        return set(0, 0, input);
    }

    default <R extends T> R set(int index, @NonNull final INPUT input) {
        return set(0, index, input);
    }

    <R extends T> R set(int block, int index, @NonNull final INPUT input);

    default <R extends T> R set(@NonNull final CharBuffer input) {
        return set(0, input);
    }

    default <R extends T> R set(@NonNull final ShortBuffer input) {
        return set(0, input);
    }

    default <R extends T> R set(@NonNull final IntBuffer input) {
        return set(0, input);
    }

    default <R extends T> R set(@NonNull final LongBuffer input) {
        return set(0, input);
    }

    default <R extends T> R set(@NonNull final FloatBuffer input) {
        return set(0, input);
    }

    default <R extends T> R set(@NonNull final DoubleBuffer input) {
        return set(0, input);
    }

    default <R extends T> R set(@NonNull final ByteBuffer input) {
        return set(0, input);
    }

    default <R extends T> R set(int blockOffset, @NonNull final CharBuffer input) {
        return set(blockOffset, MemUtils.getByteBuffer(input));
    }

    default <R extends T> R set(int blockOffset, @NonNull final ShortBuffer input) {
        return set(blockOffset, MemUtils.getByteBuffer(input));
    }

    default <R extends T> R set(int blockOffset, @NonNull final IntBuffer input) {
        return set(blockOffset, MemUtils.getByteBuffer(input));
    }

    default <R extends T> R set(int blockOffset, @NonNull final LongBuffer input) {
        return set(blockOffset, MemUtils.getByteBuffer(input));
    }

    default <R extends T> R set(int blockOffset, @NonNull final FloatBuffer input) {
        return set(blockOffset, MemUtils.getByteBuffer(input));
    }

    default <R extends T> R set(int blockOffset, @NonNull final DoubleBuffer input) {
        return set(blockOffset, MemUtils.getByteBuffer(input));
    }

    <R extends T> R set(int blockOffset, @NonNull final ByteBuffer input);

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
