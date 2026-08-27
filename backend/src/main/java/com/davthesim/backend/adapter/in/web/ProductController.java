package com.davthesim.backend.adapter.in.web;

import com.davthesim.backend.application.port.out.ProductRepository;
import com.davthesim.backend.domain.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController // Http endpoints live here, return values convert to JSON automatically.
@RequestMapping("/api/products") // Base path.
@RequiredArgsConstructor // Keeps me from having to write manual constructor boilerplate.
public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}