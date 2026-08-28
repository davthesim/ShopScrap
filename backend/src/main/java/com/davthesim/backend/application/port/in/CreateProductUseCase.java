package com.davthesim.backend.application.port.in;

import com.davthesim.backend.domain.product.Product;

public interface CreateProductUseCase {
    Product createProduct(CreateProductCommand command);
}