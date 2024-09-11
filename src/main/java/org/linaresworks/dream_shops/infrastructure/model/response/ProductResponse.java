package org.linaresworks.dream_shops.infrastructure.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.linaresworks.dream_shops.domain.entity.Category;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
    private List<ImageResponse> images;
}
