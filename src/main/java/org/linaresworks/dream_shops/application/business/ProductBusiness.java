package org.linaresworks.dream_shops.application.business;

import org.linaresworks.dream_shops.application.service.IProductService;
import org.linaresworks.dream_shops.domain.entity.Category;
import org.linaresworks.dream_shops.domain.entity.Image;
import org.linaresworks.dream_shops.domain.entity.Product;
import org.linaresworks.dream_shops.domain.repository.CategoryRepository;
import org.linaresworks.dream_shops.domain.repository.ImageRepository;
import org.linaresworks.dream_shops.domain.repository.ProductRepository;
import org.linaresworks.dream_shops.infrastructure.exception.ProductNotFoundException;
import org.linaresworks.dream_shops.infrastructure.model.mapper.ProductMapper;
import org.linaresworks.dream_shops.infrastructure.model.response.ImageResponse;
import org.linaresworks.dream_shops.infrastructure.model.response.ProductResponse;
import org.linaresworks.dream_shops.infrastructure.model.request.AddProductRequest;
import org.linaresworks.dream_shops.infrastructure.model.request.ProductUpdateRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductBusiness implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final ModelMapper modelMapper;
    private final ProductMapper productMapper;

    public ProductBusiness(ProductRepository productRepository, CategoryRepository categoryRepository, ImageRepository imageRepository, ModelMapper modelMapper, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.imageRepository = imageRepository;
        this.modelMapper = modelMapper;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public ProductResponse addProduct(AddProductRequest request) {
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        request.setCategory(category);
        return productMapper.apply(productRepository.save(createProduct(request, category)));
    }

    private Product createProduct(AddProductRequest request, Category category) {
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete,
                        () -> {
                    throw new ProductNotFoundException();
                    }
                );
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(Long id, ProductUpdateRequest request) {
        return productRepository.findById(id)
                .map(existingProduct ->
                        productMapper.apply(
                                productRepository.save(
                                        updateExistingProduct(existingProduct, request)
                                )

                        )
                ).orElseThrow(ProductNotFoundException::new);
    }

    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());

        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category).stream()
                .map(productMapper)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand).stream()
                .map(productMapper)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand).stream()
                .map(productMapper)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getProductsByName(String name) {
        return productRepository.findByName(name).stream()
                .map(productMapper)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name).stream()
                .map(productMapper)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

    //TODO: Eliminar
    private ProductResponse mapping(Product product) {
        ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);
        List<Image> images = imageRepository.findByProductId(product.getId());
        List<ImageResponse> imageResponses = images.stream()
                .map(image -> modelMapper.map(image, ImageResponse.class))
                .toList();
        productResponse.setImages(imageResponses);
        return productResponse;
    }
}
