package com.inventario.stock_flow.inventory.domain.ports;

import java.util.List;
import java.util.Optional;
import com.inventario.stock_flow.inventory.domain.model.ProductSupplier;

public interface ProductSupplierRepositoryPort {
    ProductSupplier save(ProductSupplier productSupplier);

    List<ProductSupplier> findByProductId(Long productId);

    Optional<ProductSupplier> findByProductIdAndSupplierId(Long productId, Long supplierId);
}
