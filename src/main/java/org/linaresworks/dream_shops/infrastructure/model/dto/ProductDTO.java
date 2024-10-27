package org.linaresworks.dream_shops.infrastructure.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private List<ImageDTO> image;
}
