package com.inventario.stock_flow.inventory.application.usecase;

import java.math.BigDecimal;
import com.inventario.stock_flow.inventory.domain.model.ProductSupplier;

public interface AssociateSupplierUseCase {
    ProductSupplier execute(Long productId, Long supplierId, BigDecimal cost);
}
