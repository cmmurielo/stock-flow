package com.inventario.stock_flow.inventory.infrastructure.adapter.persistence.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.inventario.stock_flow.inventory.infrastructure.adapter.persistence.entity.ProductSupplierEntity;

public interface JpaProductSupplierRepository extends JpaRepository<ProductSupplierEntity, Long> {
    List<ProductSupplierEntity> findByProductId(Long productId);

    Optional<ProductSupplierEntity> findByProductIdAndSupplierId(Long productId, Long supplierId);
}
