package com.example.Search_project.service;

import com.example.Search_project.entity.Product;
import com.example.Search_project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepositery;

    public ProductService(ProductRepository productRepository) {
        this.productRepositery = productRepository;
    }

    public Product findProductByName(String name) {
        return productRepositery.findByNameIgnoreCase(name);
    }
}
