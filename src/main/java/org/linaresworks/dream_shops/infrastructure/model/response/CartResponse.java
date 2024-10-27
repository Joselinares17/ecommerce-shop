package org.linaresworks.dream_shops.infrastructure.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
public class CartResponse {
    private Long id;
    private BigDecimal totalAmount;
    private Set<CartItemResponse> items;
}
