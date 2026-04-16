package com.inventario.stock_flow.supplier.infrastructure.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventario.stock_flow.supplier.infrastructure.adapter.persistence.entity.SupplierEntity;

public interface JpaSupplierRepository extends JpaRepository<SupplierEntity, Long> {

}
