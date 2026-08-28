package com.davthesim.backend.application.service;

import com.davthesim.backend.application.port.in.CreateProductCommand;
import com.davthesim.backend.application.port.out.ProductRepository;
import com.davthesim.backend.domain.product.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock // Builds a fake Product Repo (no db anywhere, mock just records calls and returns what was given).
    private ProductRepository productRepository;

    @InjectMocks // Constructs the REAL ProductService with the fake ProductRepository.
    private ProductService productService;

    @Test // Functions that get tested.
    void createProduct_assignsIdAndTimestamps() {
        when(productRepository.save(any(Product.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CreateProductCommand command = new CreateProductCommand(
                "Wireless Mouse", null, null,
                "https://example.com/mouse", "TestMart",
                new BigDecimal("49.99"), "USD", null
        );

        Product result = productService.createProduct(command);

        assertThat(result.id()).isNotNull(); // We assert the service's own responsibilities.
        assertThat(result.createdAt()).isNotNull();
        assertThat(result.updatedAt()).isEqualTo(result.createdAt());
        assertThat(result.name()).isEqualTo("Wireless Mouse");
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void getProducts_delegatesToRepository() {
        when(productRepository.findAll()).thenReturn(List.of());

        List<Product> result = productService.getProducts();

        assertThat(result).isEmpty();
        verify(productRepository).findAll();
    }
}