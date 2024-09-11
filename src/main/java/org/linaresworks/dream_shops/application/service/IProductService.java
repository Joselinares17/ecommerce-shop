package org.linaresworks.dream_shops.application.service;

import org.linaresworks.dream_shops.infrastructure.model.response.ProductResponse;
import org.linaresworks.dream_shops.infrastructure.model.request.AddProductRequest;
import org.linaresworks.dream_shops.infrastructure.model.request.ProductUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    ProductResponse addProduct(AddProductRequest request);
    ProductResponse getProductById(Long id);
    void deleteProductById(Long id);
    ProductResponse updateProduct(Long id, ProductUpdateRequest request);
    List<ProductResponse> getAllProducts();
    List<ProductResponse> getProductsByCategory(String category);
    List<ProductResponse> getProductsByBrand(String brand);
    List<ProductResponse> getProductsByCategoryAndBrand(String category, String brand);
    List<ProductResponse> getProductsByName(String name);
    List<ProductResponse> getProductsByBrandAndName(String brand, String name);
    Long countProductsByBrandAndName(String brand, String name);
}
