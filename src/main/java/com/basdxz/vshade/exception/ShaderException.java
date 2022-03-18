package com.basdxz.vshade.exception;

public class ShaderException extends RuntimeException {
    public ShaderException(String format, Object... args) {
        super(String.format(format, args));
    }

    public ShaderException(String message) {
        super(message);
    }
}
