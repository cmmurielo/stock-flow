package com.inventario.stock_flow.application.usecase;

import com.inventario.stock_flow.domain.model.Product;

public interface CreateProductUseCase {

    Product execute(Product product);

}
