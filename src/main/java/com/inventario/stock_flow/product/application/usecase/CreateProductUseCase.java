package com.inventario.stock_flow.product.application.usecase;

import com.inventario.stock_flow.product.domain.model.Product;

public interface CreateProductUseCase {

    Product execute(Product product);

}
