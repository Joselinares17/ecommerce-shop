package org.linaresworks.dream_shops.infrastructure.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryUpdateRequest {
    private String name;
}
