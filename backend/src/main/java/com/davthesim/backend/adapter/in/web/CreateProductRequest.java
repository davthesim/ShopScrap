package com.davthesim.backend.adapter.in.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

// Data Transfer Object to send data between the app (keeps jakarta.validation web-isms out of application layer).
public record CreateProductRequest(
        @NotBlank String name,
        String description,
        String imageUrl,
        @NotBlank String productUrl,
        @NotBlank String retailer,
        @NotNull @Positive BigDecimal priceAmount,
        @NotBlank @Pattern(regexp = "[A-Z]{3}", message = "must be a 3-letter ISO currency code") String priceCurrency,
        String region
) {
}