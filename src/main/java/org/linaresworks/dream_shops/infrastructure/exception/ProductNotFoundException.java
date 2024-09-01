package org.linaresworks.dream_shops.infrastructure.exception;

public class ProductNotFoundException extends ResourceNotFoundException{
    private static final String msg = "product not found";

    public ProductNotFoundException() {
        super(msg);
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
