package org.linaresworks.dream_shops.infrastructure.model.request;

import lombok.Builder;
import lombok.Data;
import org.linaresworks.dream_shops.domain.entity.Category;

import java.math.BigDecimal;

@Data
@Builder
public class ProductUpdateRequest {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
}
