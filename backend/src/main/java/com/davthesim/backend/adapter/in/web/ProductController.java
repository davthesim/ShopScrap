package com.davthesim.backend.adapter.in.web;

import com.davthesim.backend.application.port.in.CreateProductCommand;
import com.davthesim.backend.application.port.in.CreateProductUseCase;
import com.davthesim.backend.application.port.in.GetProductsUseCase;
import com.davthesim.backend.domain.product.Product;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Http endpoints live here, return values convert to JSON automatically.
@RequestMapping("/api/products") // Base path.
@RequiredArgsConstructor // Keeps me from having to write manual constructor boilerplate.
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final GetProductsUseCase getProductsUseCase;

    @GetMapping
    public List<Product> getProducts() {
        return getProductsUseCase.getProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Returns 201 code indicating "Resource Made".
    public Product createProduct(@Valid @RequestBody CreateProductRequest request) { // @Valid and @RequestBody parses JSON into the DTO and enforces annotations.
        CreateProductCommand command = new CreateProductCommand(
                request.name(),
                request.description(),
                request.imageUrl(),
                request.productUrl(),
                request.retailer(),
                request.priceAmount(),
                request.priceCurrency(),
                request.region()
        );
        return createProductUseCase.createProduct(command);
    }
}
