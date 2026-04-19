package com.inventario.stock_flow.application.usecase;

import com.inventario.stock_flow.domain.core.result.Result;
import com.inventario.stock_flow.domain.model.Product;

import java.util.List;

public interface ListAllProductsUseCase {
    Result<List<Product>> execute();
}
