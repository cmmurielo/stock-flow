package com.inventario.stock_flow.supplier.domain.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.inventario.stock_flow.supplier.domain.model.Supplier;

public interface SupplierRepositoryPort {
    Supplier save(Supplier supplier);

    Optional<Supplier> findById(UUID id);

    List<Supplier> findAll();
}
