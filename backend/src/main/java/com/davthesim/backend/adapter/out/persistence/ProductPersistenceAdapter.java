package com.davthesim.backend.adapter.out.persistence;

import com.davthesim.backend.application.port.out.ProductRepository;
import com.davthesim.backend.domain.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductRepository {

    private final SpringDataProductRepository springDataRepository;

    @Override
    public Product save(Product product) {
        ProductEntity saved = springDataRepository.save(toEntity(product));
        return toDomain(saved);
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return springDataRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return springDataRepository.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    private ProductEntity toEntity(Product product) {
        return new ProductEntity(
                product.id(),
                product.name(),
                product.description(),
                product.imageUrl(),
                product.productUrl(),
                product.retailer(),
                product.priceAmount(),
                product.priceCurrency(),
                product.region(),
                product.createdAt(),
                product.updatedAt()
        );
    }

    private Product toDomain(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getImageUrl(),
                entity.getProductUrl(),
                entity.getRetailer(),
                entity.getPriceAmount(),
                entity.getPriceCurrency(),
                entity.getRegion(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}