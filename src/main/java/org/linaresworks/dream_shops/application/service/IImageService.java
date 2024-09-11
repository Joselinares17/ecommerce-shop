package org.linaresworks.dream_shops.application.service;

import org.linaresworks.dream_shops.domain.entity.Image;
import org.linaresworks.dream_shops.infrastructure.model.response.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageResponse> savesImage(List<MultipartFile> file, Long productId);
    void updateImage(MultipartFile file, Long imageId);
}
