package com.inventario.stock_flow.domain.ports;

import java.util.List;
import java.util.Optional;
import com.inventario.stock_flow.domain.model.Product;

public interface ProductRepositoryPort {
    Product save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();
}
