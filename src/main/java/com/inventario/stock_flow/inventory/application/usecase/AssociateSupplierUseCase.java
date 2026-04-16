package com.inventario.stock_flow.inventory.application.usecase;

import java.math.BigDecimal;
import java.util.UUID;

import com.inventario.stock_flow.inventory.domain.model.ProductSupplier;

public interface AssociateSupplierUseCase {
    ProductSupplier execute(UUID productId, UUID supplierId, BigDecimal cost);
}
