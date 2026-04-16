package com.inventario.stock_flow.inventory.infrastructure.adapter.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventario.stock_flow.inventory.infrastructure.adapter.persistence.entity.ProductSupplierEntity;

public interface JpaProductSupplierRepository extends JpaRepository<ProductSupplierEntity, UUID> {
    List<ProductSupplierEntity> findByProductId(UUID productId);

    Optional<ProductSupplierEntity> findByProductIdAndSupplierId(UUID productId, UUID supplierId);
}
