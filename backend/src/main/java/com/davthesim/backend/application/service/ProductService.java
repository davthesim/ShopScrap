package com.davthesim.backend.application.service;

import com.davthesim.backend.application.port.in.CreateProductCommand;
import com.davthesim.backend.application.port.in.CreateProductUseCase;
import com.davthesim.backend.application.port.in.GetProductsUseCase;
import com.davthesim.backend.application.port.out.ProductRepository;
import com.davthesim.backend.domain.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService implements CreateProductUseCase, GetProductsUseCase {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(CreateProductCommand command) {
        Instant now = Instant.now(); // Creation time.
        Product product = new Product(
                UUID.randomUUID(), // Generate a random UUID on the app side.
                command.name(),
                command.description(),
                command.imageUrl(),
                command.productUrl(),
                command.retailer(),
                command.priceAmount(),
                command.priceCurrency(),
                command.region(),
                now,
                now
        );
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}