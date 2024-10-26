package org.linaresworks.dream_shops.application.business.assembler;

import org.linaresworks.dream_shops.domain.entity.Image;
import org.linaresworks.dream_shops.domain.repository.ImageRepository;
import org.linaresworks.dream_shops.infrastructure.model.mapper.ImageMapper;
import org.linaresworks.dream_shops.infrastructure.model.response.ImageResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductAssemblerService {
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    public ProductAssemblerService(ImageRepository imageRepository, ImageMapper imageMapper) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
    }

    public List<ImageResponse> getProductImages(Long productId) {
        return imageRepository.existsByProductId(productId)
                ? imageRepository.findByProductId(productId).stream().map(imageMapper).toList()
                : new ArrayList<>();
    }

    public List<Image> mapImages(List<ImageResponse> response) {
        return Optional.ofNullable(response)
                .map(images -> images.stream()
                        .map(imageMapper::fromResponse)
                        .toList())
                .orElse(new ArrayList<>());
    }
}
