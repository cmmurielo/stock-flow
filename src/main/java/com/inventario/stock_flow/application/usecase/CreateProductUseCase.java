package com.inventario.stock_flow.application.usecase;

import com.inventario.stock_flow.domain.core.result.Result;
import com.inventario.stock_flow.domain.model.Product;

public interface CreateProductUseCase {
    Result<Product> execute(Product product);
}
