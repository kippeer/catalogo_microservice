package com.example.productcatalog.service;

import com.example.productcatalog.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface ProductService {
    Product createProduct(Product product);
    Product updateProduct(String id, Product product);
    void deleteProduct(String id);
    Product getProduct(String id);
    Page<Product> searchProducts(String name, String category,
                                 BigDecimal minPrice, BigDecimal maxPrice,
                                 Pageable pageable);
}