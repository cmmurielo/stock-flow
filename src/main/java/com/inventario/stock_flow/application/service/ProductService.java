package com.inventario.stock_flow.application.service;

import com.inventario.stock_flow.application.usecase.ListAllProductsUseCase;
import org.springframework.stereotype.Service;

import com.inventario.stock_flow.application.usecase.CreateProductUseCase;
import com.inventario.stock_flow.domain.core.result.Result;
import com.inventario.stock_flow.domain.model.Product;
import com.inventario.stock_flow.domain.ports.ProductRepositoryPort;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements CreateProductUseCase, ListAllProductsUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    @Transactional
    public Result<Product> execute(Product product) {
        Product saved = productRepositoryPort.save(product);
        return Result.ok(saved);
    }

    @Override
    public Result<List<Product>> execute() {
        List<Product> products = productRepositoryPort.findAll();
        return Result.ok(products);
    }
}
