package org.linaresworks.dream_shops.infrastructure.model.mapper;

import org.linaresworks.dream_shops.domain.entity.CartItem;
import org.linaresworks.dream_shops.infrastructure.model.response.CartItemResponse;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CartItemMapper implements Function<CartItem, CartItemResponse> {
    private final ProductMapper productMapper;

    public CartItemMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public CartItemResponse apply(CartItem cartItem) {
        if(cartItem == null) return null;

        return new CartItemResponse(
                cartItem.getId(),
                cartItem.getQuantity(),
                cartItem.getUnitPrice(),
                cartItem.getTotalPrice(),
                productMapper.toProductDTO(cartItem.getProduct())
        );
    }

    public CartItem fromResponse(CartItemResponse cartItemResponse) {
        return new CartItem(
                cartItemResponse.getId(),
                cartItemResponse.getQuantity(),
                cartItemResponse.getUnitPrice(),
                cartItemResponse.getTotalPrice(),
                productMapper.fromProductDTO(cartItemResponse.getProduct())
        );
    }
}
