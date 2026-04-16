package com.inventario.stock_flow.supplier.application.usecase;

import com.inventario.stock_flow.supplier.domain.model.Supplier;

public interface CreateSupplierUseCase {
    Supplier execute(Supplier supplier);

}
