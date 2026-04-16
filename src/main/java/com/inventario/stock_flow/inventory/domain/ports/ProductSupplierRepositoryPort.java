package com.inventario.stock_flow.inventory.domain.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.inventario.stock_flow.inventory.domain.model.ProductSupplier;

public interface ProductSupplierRepositoryPort {
    ProductSupplier save(ProductSupplier productSupplier);

    List<ProductSupplier> findByProductId(UUID productId);

    Optional<ProductSupplier> findByProductIdAndSupplierId(UUID productId, UUID supplierId);
}
