package com.davthesim.backend.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

// Spring auto-hooks/creates implementation for save, findById, findAll, delete, etc, by extending.
public interface SpringDataProductRepository extends JpaRepository<ProductEntity, UUID> {}
