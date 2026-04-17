package com.inventario.stock_flow.application.usecase;

import java.math.BigDecimal;
import com.inventario.stock_flow.domain.model.ProductSupplier;

public interface AssociateSupplierUseCase {
    ProductSupplier execute(Long productId, Long supplierId, BigDecimal cost);
}
