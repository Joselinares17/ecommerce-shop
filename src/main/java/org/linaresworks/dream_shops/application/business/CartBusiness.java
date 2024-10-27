package org.linaresworks.dream_shops.application.business;

import org.linaresworks.dream_shops.application.service.ICartService;
import org.linaresworks.dream_shops.domain.entity.Cart;
import org.linaresworks.dream_shops.domain.repository.CartItemRepository;
import org.linaresworks.dream_shops.domain.repository.CartRepository;
import org.linaresworks.dream_shops.infrastructure.exception.CartNotFoundException;
import org.linaresworks.dream_shops.infrastructure.model.mapper.CartMapper;
import org.linaresworks.dream_shops.infrastructure.model.response.CartItemResponse;
import org.linaresworks.dream_shops.infrastructure.model.response.CartResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CartBusiness implements ICartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartMapper cartMapper;

    public CartBusiness(CartRepository cartRepository, CartItemRepository cartItemRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartMapper = cartMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Cart getCart(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(CartNotFoundException::new);
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void clearCart(Long id) {
        CartResponse cart = getCart(id);
        cartItemRepository.deleteAllByCartId(id);
        cart.getItems().clear();
        cartRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getTotalPrice(Long id) {
        CartResponse cart = getCart(id);
        return cart.getItems().stream()
                .map(CartItemResponse::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
