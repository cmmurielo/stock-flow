package com.inventario.stock_flow.product.infrastructure.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventario.stock_flow.product.infrastructure.adapter.persistence.entity.ProductEntity;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {

}
