//package com.example.Search_project.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//@Service
//public class OllamaService {
//
//
//        private final WebClient webclient;
//
//    public OllamaService(WebClient webclient) {
//        this.webclient = webclient;
//    }
//    public String askOllama(String prompt) {
//
//        String requestJson = String.format(
//                "{\"model\": \"llama3\", \"prompt\": \"%s\"}",
//                prompt
//        );
//        return webclient.post()
//                .uri("/api/generate")
//                .header("content-Type","application/json")
//                .body(Mono.just(requestJson),String.class)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//    }
//}
//
