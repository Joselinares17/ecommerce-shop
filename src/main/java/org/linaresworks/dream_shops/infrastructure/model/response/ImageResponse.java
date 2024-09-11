package org.linaresworks.dream_shops.infrastructure.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse {
    private Long imageId;
    private String imageName;
    private String downloadUrl;
}
