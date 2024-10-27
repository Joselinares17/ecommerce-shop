package org.linaresworks.dream_shops.infrastructure.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.linaresworks.dream_shops.infrastructure.model.dto.ProductDTO;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CartItemResponse {
    private Long id;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private ProductDTO product;
}
