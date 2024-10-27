package org.linaresworks.dream_shops.application.business.assembler;

import org.linaresworks.dream_shops.domain.entity.CartItem;
import org.linaresworks.dream_shops.infrastructure.model.mapper.ProductMapper;
import org.linaresworks.dream_shops.infrastructure.model.response.CartItemResponse;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CartAssemblerService {
    private final ProductMapper productMapper;

    public CartAssemblerService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    /**
     * Converts <code>Set&lt;CartItem&gt;</code> objects into a <code>Set&lt;CartItemResponse&gt;</code> objects.
     * If the set of CartItem is present, returns a Set describing the result of applying the given mapping function to the value,
     * otherwise returns an empty Set. If the mapping function returns a null result then this method returns an empty Set.
     *
     * @param items the set of CartItem objects to convert
     * @return a set of CartItemResponse objects
     */
    public Set<CartItemResponse> mapToCartItemResponse(Set<CartItem> items) {
        if(items == null) return new HashSet<>();

        return items.stream()
                .map(item -> new CartItemResponse(
                        item.getId(),
                        item.getQuantity(),
                        item.getUnitPrice(),
                        item.getTotalPrice(),
                        productMapper.toProductDTO(item.getProduct())
                ))
                .collect(Collectors.toSet());
    }

    /**
     * Converts a <code>Set&lt;CartItemResponse&gt;</code> objects into a <code>Set&lt;CartItem&gt;</code> objects.
     * If the set of CartItemResponse is present, returns a Set describing the result of applying the given mapping function to the value,
     * otherwise returns an empty Set. If the mapping function returns a null result then this method returns an empty Set.
     *
     * @param items the set of CartItemResponse objects to convert
     * @return a set of CartItem entities
     */
    public Set<CartItem> mapFromCartItemResponse(Set<CartItemResponse> items) {
        if(items == null) return new HashSet<>();

        return items.stream()
                .map(item -> new CartItem(
                        item.getId(),
                        item.getQuantity(),
                        item.getUnitPrice(),
                        item.getTotalPrice(),
                        productMapper.fromProductDTO(item.getProduct())
                ))
                .collect(Collectors.toSet());
    }
}
