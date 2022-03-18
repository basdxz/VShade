package com.basdxz.vbuffers.common;

import lombok.*;
import sun.nio.ch.DirectBuffer;

import java.nio.*;

import static com.basdxz.vbuffers.common.Constants.*;

public class MemUtils {
    public static float[] newArrayFromBuffer(FloatBuffer buffer) {
        buffer.position(0);
        val array = new float[buffer.remaining()];
        while (buffer.hasRemaining()) {
            array[buffer.position()] = buffer.get();
        }
        return array;
    }

    public static int[] newArrayFromBuffer(IntBuffer buffer) {
        buffer.position(0);
        val array = new int[buffer.remaining()];
        while (buffer.hasRemaining()) {
            array[buffer.position()] = buffer.get();
        }
        return array;
    }

    public static DoubleBuffer newBufferFromValue(double... value) {
        val buffer = newDoubleBuffer(value.length);
        buffer.put(value);
        buffer.position(0);
        return buffer;
    }

    public static FloatBuffer newBufferFromValue(float... value) {
        val buffer = newFloatBuffer(value.length);
        buffer.put(value);
        buffer.position(0);
        return buffer;
    }

    public static LongBuffer newBufferFromValue(long... value) {
        val buffer = newLongBuffer(value.length);
        buffer.put(value);
        buffer.position(0);
        return buffer;
    }

    public static IntBuffer newBufferFromValue(int... value) {
        val buffer = newIntBuffer(value.length);
        buffer.put(value);
        buffer.position(0);
        return buffer;
    }

    public static ShortBuffer newBufferFromValue(short... value) {
        val buffer = newShortBuffer(value.length);
        buffer.put(value);
        buffer.position(0);
        return buffer;
    }

    public static ByteBuffer newBufferFromValue(byte... value) {
        val buffer = newByteBuffer(value.length);
        buffer.put(value);
        buffer.position(0);
        return buffer;
    }

    public static DoubleBuffer newDoubleBuffer(int length) {
        return newByteBuffer(DOUBLE_SIZE * length).asDoubleBuffer();
    }

    public static FloatBuffer newFloatBuffer(int length) {
        return newByteBuffer(FLOAT_SIZE * length).asFloatBuffer();
    }

    public static LongBuffer newLongBuffer(int length) {
        return newByteBuffer(LONG_SIZE * length).asLongBuffer();
    }

    public static IntBuffer newIntBuffer(int length) {
        return newByteBuffer(INT_SIZE * length).asIntBuffer();
    }

    public static ShortBuffer newShortBuffer(int length) {
        return newByteBuffer(SHORT_SIZE * length).asShortBuffer();
    }

    public static ByteBuffer newByteBuffer(int length) {
        return ByteBuffer.allocateDirect(length).order(ByteOrder.nativeOrder());
    }

    public static ByteBuffer getByteBuffer(Buffer buffer) {
        if (!(buffer instanceof DirectBuffer))
            throw new IllegalArgumentException("Buffer must me direct buffer!");
        return (ByteBuffer) ((DirectBuffer) buffer).attachment();
    }
}
