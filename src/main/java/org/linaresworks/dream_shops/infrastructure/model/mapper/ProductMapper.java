package org.linaresworks.dream_shops.infrastructure.model.mapper;

import org.linaresworks.dream_shops.domain.entity.Image;
import org.linaresworks.dream_shops.domain.entity.Product;
import org.linaresworks.dream_shops.domain.repository.ImageRepository;
import org.linaresworks.dream_shops.infrastructure.model.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class ProductMapper implements Function<Product, ProductResponse> {
    private final ImageMapper imageMapper;
    private final ImageRepository imageRepository;

    public ProductMapper(ImageMapper imageMapper, ImageRepository imageRepository) {
        this.imageMapper = imageMapper;
        this.imageRepository = imageRepository;
    }

    @Override
    public ProductResponse apply(Product product) {
        List<Image> images = imageRepository.findByProductId(product.getId());

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getPrice(),
                product.getInventory(),
                product.getDescription(),
                product.getCategory(),
                images.stream()
                        .map(imageMapper)
                        .toList()
        );
    }

    public Product fromResponse(ProductResponse response) {
        return new Product(
                response.getId(),
                response.getName(),
                response.getBrand(),
                response.getPrice(),
                response.getInventory(),
                response.getDescription(),
                response.getCategory(),
                response.getImages().stream()
                        .map(imageMapper::fromResponse)
                        .toList()
        );
    }
}
