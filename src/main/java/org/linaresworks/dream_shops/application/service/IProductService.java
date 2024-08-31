package org.linaresworks.dream_shops.application.service;

import org.linaresworks.dream_shops.domain.entity.Product;
import org.linaresworks.dream_shops.infrastructure.model.request.AddProductRequest;
import org.linaresworks.dream_shops.infrastructure.model.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest request);
    Product getProductById(Long id);
    void deleteProductById(Long id, Product product);
    Product updateProduct(Long id, ProductUpdateRequest request);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);
    Long countProductsByBrandAndName(String brand, String name);
}
