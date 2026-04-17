package com.inventario.stock_flow.domain.ports;

import java.util.List;
import java.util.Optional;
import com.inventario.stock_flow.domain.model.Supplier;

public interface SupplierRepositoryPort {
    Supplier save(Supplier supplier);

    Optional<Supplier> findById(Long id);

    List<Supplier> findAll();
}
