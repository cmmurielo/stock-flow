package com.inventario.stock_flow.application.usecase;

import com.inventario.stock_flow.domain.core.result.Result;
import com.inventario.stock_flow.domain.model.Supplier;

public interface CreateSupplierUseCase {
    Result<Supplier> execute(Supplier supplier);
}
