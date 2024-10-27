package org.linaresworks.dream_shops.infrastructure.model.mapper;

import org.linaresworks.dream_shops.application.business.assembler.ProductAssemblerService;
import org.linaresworks.dream_shops.domain.entity.Product;
import org.linaresworks.dream_shops.infrastructure.model.dto.ProductDTO;
import org.linaresworks.dream_shops.infrastructure.model.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductMapper implements Function<Product, ProductResponse> {
    private final ProductAssemblerService productAssembler;
    private final CategoryMapper categoryMapper;
    private final ImageMapper imageMapper;

    public ProductMapper(ProductAssemblerService productAssembler, CategoryMapper categoryMapper, ImageMapper imageMapper) {
        this.productAssembler = productAssembler;
        this.categoryMapper = categoryMapper;
        this.imageMapper = imageMapper;
    }

    @Override
    public ProductResponse apply(Product product) {
        if(product == null) return null;

    return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getBrand(),
            product.getPrice(),
            product.getInventory(),
            product.getDescription(),
            categoryMapper.apply(product.getCategory()),
            productAssembler.getProductImages(product.getId()));
    }

    public Product fromResponse(ProductResponse response) {
        if(response == null) return null;

        return new Product(
                response.getId(),
                response.getName(),
                response.getBrand(),
                response.getPrice(),
                response.getInventory(),
                response.getDescription(),
                categoryMapper.fromResponse(response.getCategory()),
                productAssembler.mapFromImageResponse(response.getImages())
        );
    }

    public ProductDTO toProductDTO(Product product) {
        if(product == null) return null;

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getPrice(),
                productAssembler.mapToImageDTO(product.getImages())
        );
    }

    public Product fromProductDTO(ProductDTO productDTO) {
        if(productDTO == null) return null;

        return new Product(
                productDTO.getId(),
                productDTO.getName(),
                productDTO.getBrand(),
                productDTO.getPrice(),
                productAssembler.mapFromImageDTO(productDTO.getImage())
        );
    }

    public Product fromProductResponse(ProductResponse response) {
        if(response == null) return null;

        return new Product(
                response.getId(),
                response.getName(),
                response.getBrand(),
                response.getPrice(),
                response.getInventory(),
                response.getDescription(),
                categoryMapper.fromResponse(response.getCategory()),
                productAssembler.mapFromImageResponse(response.getImages())
        );
    }
}
