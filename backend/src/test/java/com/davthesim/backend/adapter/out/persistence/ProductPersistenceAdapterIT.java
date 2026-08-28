package com.davthesim.backend.adapter.out.persistence;

import com.davthesim.backend.domain.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest // Makes Flyway replay V1 and V2, etc, from zero.
@Testcontainers // Containers are destroyed after-wards (dev database is untouched).
class ProductPersistenceAdapterIT { // Integration test (checking to make sure migration history builds working schema and persistence layer exists).

    @Container // Creates a new PostgreSQL 16 in Docker for this test run.
    @ServiceConnection // Tells Spring Boot to point the datasource at that container instead of application.yaml.
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");

    @Autowired
    private ProductPersistenceAdapter adapter;

    @Test
    void savesAndFindsProduct() {
        Instant now = Instant.now();
        Product product = new Product(
                UUID.randomUUID(), "IT Keyboard", "mechanical", null,
                "https://example.com/kb", "TestMart",
                new BigDecimal("89.99"), "USD", "CA",
                now, now
        );

        adapter.save(product);

        Optional<Product> found = adapter.findById(product.id());
        assertThat(found).isPresent();
        assertThat(found.get().name()).isEqualTo("IT Keyboard");
        assertThat(found.get().priceAmount()).isEqualByComparingTo(new BigDecimal("89.99"));

        List<Product> all = adapter.findAll();
        assertThat(all).isNotEmpty();
    }
}