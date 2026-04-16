package com.inventario.stock_flow.product.domain.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.inventario.stock_flow.product.domain.model.Product;

public interface ProductRepositoryPort {
    Product save(Product product);

    Optional<Product> findById(UUID id);

    List<Product> findAll();
}
