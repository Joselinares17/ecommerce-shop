package org.linaresworks.dream_shops.application.business;

import org.linaresworks.dream_shops.application.service.ICartItemService;
import org.linaresworks.dream_shops.application.service.IProductService;
import org.linaresworks.dream_shops.domain.entity.Cart;
import org.linaresworks.dream_shops.domain.entity.CartItem;
import org.linaresworks.dream_shops.domain.repository.CartItemRepository;
import org.linaresworks.dream_shops.domain.repository.CartRepository;
import org.linaresworks.dream_shops.infrastructure.model.mapper.CartItemMapper;
import org.linaresworks.dream_shops.infrastructure.model.mapper.CartMapper;
import org.linaresworks.dream_shops.infrastructure.model.mapper.ProductMapper;
import org.linaresworks.dream_shops.infrastructure.model.response.CartItemResponse;
import org.linaresworks.dream_shops.infrastructure.model.response.CartResponse;
import org.linaresworks.dream_shops.infrastructure.model.response.ProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartItemBusiness implements ICartItemService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final IProductService productService;
    private final CartBusiness cartBusiness;
    private final ProductMapper productMapper;

    public CartItemBusiness(CartRepository cartRepository, CartItemRepository cartItemRepository, IProductService productService, CartBusiness cartBusiness, ProductMapper productMapper) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
        this.cartBusiness = cartBusiness;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public void addItemToCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartBusiness.getCart(cartId);
        ProductResponse product = productService.getProductById(productId);
        CartItem cartItem = cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst().orElse(new CartItem());
        if (cartItem.getId() == null) {
            cartItem.setCart(cart);
            cartItem.setProduct(productMapper.fromProductResponse(product));
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(product.getPrice());
        }
        else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cartItem.setTotalPrice();
        cart.addItem(cartItem);
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void removeItemFromCart(Long cartId, Long productId) {

    }

    @Override
    @Transactional
    public void updateItemQuantity(Long cartId, Long productId, int quantity) {

    }
}
