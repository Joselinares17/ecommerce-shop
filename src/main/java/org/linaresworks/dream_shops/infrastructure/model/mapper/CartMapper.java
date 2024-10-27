package org.linaresworks.dream_shops.infrastructure.model.mapper;

import org.linaresworks.dream_shops.application.business.assembler.CartAssemblerService;
import org.linaresworks.dream_shops.domain.entity.Cart;
import org.linaresworks.dream_shops.infrastructure.model.response.CartResponse;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CartMapper implements Function<Cart, CartResponse> {
    private final CartAssemblerService cartAssemblerService;

    public CartMapper(CartAssemblerService cartAssemblerService) {
        this.cartAssemblerService = cartAssemblerService;
    }

    @Override
    public CartResponse apply(Cart cart) {
        return new CartResponse(
                cart.getId(),
                cart.getTotalAmount(),
                cartAssemblerService.mapToCartItemResponse(cart.getItems())
        );
    }

    public Cart fromResponse(CartResponse cartResponse) {
        return new Cart(
                cartResponse.getId(),
                cartResponse.getTotalAmount(),
                cartAssemblerService.mapFromCartItemResponse(cartResponse.getItems())
        );
    }
}
