package com.example.Search_project.service;

import com.example.Search_project.entity.Product;
import com.example.Search_project.repository.ProductRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepositery;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public ProductService(ProductRepository productRepository) {

        this.productRepositery = productRepository;
    }

    public Product findProductByName(String name) {
        return productRepositery.findByNameIgnoreCase(name);
    }


    public String saveImage(MultipartFile file) throws IOException {
        Path path = Paths.get(uploadDir);
        if(!Files.exists(path)){
            Files.createDirectories(path);
        }
        String fileName = file.getOriginalFilename();
        Path filePath = path.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return filePath.toString();
    }
}
