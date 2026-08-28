package com.davthesim.backend.application.port.in;

import java.math.BigDecimal;

// Defined as a record to pass immutable data (all declared fields are private final).
public record CreateProductCommand(
        String name,
        String description,
        String imageUrl,
        String productUrl,
        String retailer,
        BigDecimal priceAmount,
        String priceCurrency,
        String region
) {
}