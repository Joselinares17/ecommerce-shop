package org.linaresworks.dream_shops.infrastructure.exception;

public class ResourceNotFoundException extends RuntimeException{
    private static final String msg = "resource not found";

    public ResourceNotFoundException() {
        super(msg);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
