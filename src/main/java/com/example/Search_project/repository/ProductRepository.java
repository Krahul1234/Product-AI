package com.example.Search_project.repository;

import com.example.Search_project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByNameIgnoreCase(String name);
}
