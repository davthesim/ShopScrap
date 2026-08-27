package com.davthesim.backend.domain.product;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record Product(
        UUID id,
        String name,
        String description,
        String imageUrl,
        String productUrl,
        String retailer,
        BigDecimal priceAmount,
        String priceCurrency,
        String region,
        Instant createdAt,
        Instant updatedAt
) {
}