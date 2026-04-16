package com.inventario.stock_flow.product.application.service;

import org.springframework.stereotype.Service;

import com.inventario.stock_flow.product.application.usecase.CreateProductUseCase;
import com.inventario.stock_flow.product.domain.model.Product;
import com.inventario.stock_flow.product.domain.ports.ProductRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements CreateProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public Product execute(Product product) {
        return productRepositoryPort.save(product);
    }

}
