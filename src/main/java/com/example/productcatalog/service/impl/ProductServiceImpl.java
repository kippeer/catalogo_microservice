package com.example.productcatalog.service.impl;

import com.example.productcatalog.domain.Product;
import com.example.productcatalog.repository.ProductRepository;
import com.example.productcatalog.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository productRepository;
    
    @Override
    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    @Override
    @Transactional
    public Product updateProduct(String id, Product product) {
        Product existingProduct = getProduct(id);
        
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setSku(product.getSku());
        existingProduct.setStockQuantity(product.getStockQuantity());
        
        return productRepository.save(existingProduct);
    }
    
    @Override
    @Transactional
    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
    
    @Override
    public Product getProduct(String id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }
    
    @Override
    public Page<Product> searchProducts(String name, String category, 
                                      BigDecimal minPrice, BigDecimal maxPrice, 
                                      Pageable pageable) {
        Specification<Product> spec = Specification.where(null);
        
        if (name != null && !name.isEmpty()) {
            spec = spec.and((root, query, cb) -> 
                cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }
        
        if (category != null && !category.isEmpty()) {
            spec = spec.and((root, query, cb) -> 
                cb.equal(root.get("category"), category));
        }
        
        if (minPrice != null) {
            spec = spec.and((root, query, cb) -> 
                cb.greaterThanOrEqualTo(root.get("price"), minPrice));
        }
        
        if (maxPrice != null) {
            spec = spec.and((root, query, cb) -> 
                cb.lessThanOrEqualTo(root.get("price"), maxPrice));
        }
        
        return productRepository.findAll(spec, pageable);
    }
}