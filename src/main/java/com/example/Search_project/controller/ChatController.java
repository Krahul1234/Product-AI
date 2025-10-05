package com.example.Search_project.controller;

import com.example.Search_project.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/uploads")
public class ChatController {
private final ChatClient chatClient;

@Value("${file.upload-dir}")
private String uploadDir;
@Autowired
private ProductService productService;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("You are an AI assistant answering questions about different products")
                .build();
    }


@GetMapping("generate")
    public ResponseEntity<?> generate(@RequestParam(value = "message") String message){
     try {
         String response = chatClient.prompt()
                 .user(message)
                 .functions("getProductDetails")
                 .call()
                 .content()
                 .toString();

         return ResponseEntity.ok(response);
     } catch (Exception e) {
         e.printStackTrace();
         return ResponseEntity.internalServerError().body("An error occurred: " + e.getMessage());
     }
}
  @PostMapping("image")
    public ResponseEntity<String> saveImage(@RequestParam("file") MultipartFile file){
    try{
        String filePath = productService.saveImage(file);
        return ResponseEntity.ok("Image uploaded successfully: " + filePath);
    } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");
    }
    }

    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> getImageURL(@PathVariable String filename) {
        try{
            Path filePath = Paths.get(uploadDir).resolve(filename);
            UrlResource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body((Resource) resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
  }


