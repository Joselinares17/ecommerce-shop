package org.linaresworks.dream_shops.infrastructure.exception;

public class AlreadyExistsException extends RuntimeException{
    private static final String msg = "already exists";

    public AlreadyExistsException() {
        super(msg);
    }

    public AlreadyExistsException(String message) {
        super(message);
    }
}
