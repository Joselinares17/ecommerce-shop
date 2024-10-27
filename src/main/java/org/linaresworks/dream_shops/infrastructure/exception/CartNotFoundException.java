package org.linaresworks.dream_shops.infrastructure.exception;

public class CartNotFoundException extends ResourceNotFoundException{
    private static final String msg = "cart not found";

    public CartNotFoundException() {
        super(msg);
    }

    public CartNotFoundException(String message) {
        super(message);
    }
}
