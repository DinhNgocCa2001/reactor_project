package com.example.demo.controller;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("cadn")
public class TestController {
    @PostMapping("test")
    public Flux<String> getProcessedUsers() {
        return Flux.just("alice", "bob", "charlie", "david", "eve")
                .filter(name -> name.length() > 3)                      // Lọc tên dài hơn 3 ký tự
                .delayElements(Duration.ofMillis(300))                 // Giả lập xử lý bất đồng bộ
                .flatMap(this::enrichUserAsync, 4)                        // Giả lập gọi API enrich từng user
                .map(name -> name.toUpperCase())                       // Biến thành chữ in hoa
                .log();                                                // In log các bước
    }

    // Giả lập xử lý bất đồng bộ cho từng user
    private Mono<String> enrichUserAsync(String name) {
        return Mono.just("User: " + name)
                .delayElement(Duration.ofMillis(200)); // mỗi user chậm thêm 200ms
    }
}

