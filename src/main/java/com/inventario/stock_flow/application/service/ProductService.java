package com.inventario.stock_flow.application.service;

import org.springframework.stereotype.Service;

import com.inventario.stock_flow.application.usecase.CreateProductUseCase;
import com.inventario.stock_flow.domain.model.Product;
import com.inventario.stock_flow.domain.ports.ProductRepositoryPort;

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
