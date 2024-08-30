package org.linaresworks.dream_shops.application.business;

import org.linaresworks.dream_shops.application.service.IProductService;
import org.linaresworks.dream_shops.domain.entity.Product;
import org.linaresworks.dream_shops.domain.repository.ProductRepository;
import org.linaresworks.dream_shops.infrastructure.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductBusiness implements IProductService {
    private final ProductRepository productRepository;

    public ProductBusiness(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public void deleteProductById(Long id, Product product) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete,
                        () -> {
                    throw new ProductNotFoundException();
                    }
                );
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }
}
