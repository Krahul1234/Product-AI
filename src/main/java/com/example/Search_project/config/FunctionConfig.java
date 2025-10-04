package com.example.Search_project.config;

import com.example.Search_project.entity.Product;
import com.example.Search_project.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;
@Configuration
public class FunctionConfig {

//    @Bean
//    public WebClient webClient(WebClient.Builder builder) {
//        return builder
//                .baseUrl("http://localhost:11434") // Ollama base URL
//                .build();
//    }
    @Autowired
    private final ProductService productService;

    public FunctionConfig(ProductService productService) {
        this.productService = productService;
    }

    public record ProductName(String name) {}
    public record ProductDetails(int id, String name, int price, int quantity){}

    @Bean
    @Description("Get product details by name")
    public Function<ProductName,ProductDetails> getProductDetails(){
        return productName -> {
            Product product = productService.findProductByName(productName.name());
            if(product!=null){
                return new ProductDetails(product.getId(), product.getName(), product.getPrice(), product.getQuantity());
            }
            else {
                return new ProductDetails(0,"Not found",0,0 );
            }
        };
    }

}
