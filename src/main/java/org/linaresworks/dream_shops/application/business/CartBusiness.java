package org.linaresworks.dream_shops.application.business;

import org.linaresworks.dream_shops.application.service.ICartService;
import org.linaresworks.dream_shops.domain.entity.Cart;
import org.linaresworks.dream_shops.domain.repository.CartRepository;
import org.linaresworks.dream_shops.infrastructure.exception.CartNotFoundException;
import org.linaresworks.dream_shops.infrastructure.model.mapper.CartMapper;
import org.linaresworks.dream_shops.infrastructure.model.response.CartResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CartBusiness implements ICartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    public CartBusiness(CartRepository cartRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public CartResponse getCart(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(CartNotFoundException::new);
        cart.setTotalAmount();
        return null;
    }

    @Override
    @Transactional
    public void clearCart(Long id) {

    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getTotalPrice(Long id) {
        return null;
    }
}
