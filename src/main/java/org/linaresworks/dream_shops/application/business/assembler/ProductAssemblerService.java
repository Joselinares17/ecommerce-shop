package org.linaresworks.dream_shops.application.business.assembler;

import org.linaresworks.dream_shops.domain.entity.Image;
import org.linaresworks.dream_shops.domain.repository.ImageRepository;
import org.linaresworks.dream_shops.infrastructure.model.dto.ImageDTO;
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

    public List<Image> mapFromImageResponse(List<ImageResponse> response) {
        return Optional.ofNullable(response)
                .map(images -> images.stream()
                        .map(imageMapper::fromResponse)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<Image> mapFromImageDTO(List<ImageDTO> dto) {
        return Optional.ofNullable(dto)
                .map(images -> images.stream()
                        .map(imageMapper::fromImageDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<ImageDTO> mapToImageDTO(List<Image> images) {
        return Optional.ofNullable(images)
                .map(imageList -> imageList.stream()
                        .map(imageMapper::toImageDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }
}
