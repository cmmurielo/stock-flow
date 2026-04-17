package com.inventario.stock_flow.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventario.stock_flow.infrastructure.persistence.entity.ProductEntity;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {

}
