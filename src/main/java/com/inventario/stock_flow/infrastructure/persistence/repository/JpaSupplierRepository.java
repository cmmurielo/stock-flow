package com.inventario.stock_flow.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventario.stock_flow.infrastructure.persistence.entity.SupplierEntity;

public interface JpaSupplierRepository extends JpaRepository<SupplierEntity, Long> {

}
