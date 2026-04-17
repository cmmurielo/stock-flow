package com.inventario.stock_flow.application.usecase;

import com.inventario.stock_flow.domain.model.Supplier;

public interface CreateSupplierUseCase {
    Supplier execute(Supplier supplier);

}
