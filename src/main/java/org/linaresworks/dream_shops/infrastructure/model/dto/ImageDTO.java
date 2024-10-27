package org.linaresworks.dream_shops.infrastructure.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Blob;

@Data
@AllArgsConstructor
public class ImageDTO {
    private Long id;
    private String fileName;
    private String fileType;
    private Blob image;
    private String downloadUrl;
}
