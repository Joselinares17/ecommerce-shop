package org.linaresworks.dream_shops.application.service;

import org.linaresworks.dream_shops.infrastructure.model.response.CartResponse;

import java.math.BigDecimal;

public interface ICartService {
    CartResponse getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);
}
