package com.davthesim.backend.application.port.in;

import com.davthesim.backend.domain.product.Product;

import java.util.List;

public interface GetProductsUseCase {
    List<Product> getProducts();
}