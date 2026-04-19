package com.inventario.stock_flow.application.service;

import org.springframework.stereotype.Service;

import com.inventario.stock_flow.application.usecase.CreateSupplierUseCase;
import com.inventario.stock_flow.domain.core.result.Result;
import com.inventario.stock_flow.domain.model.Supplier;
import com.inventario.stock_flow.domain.ports.SupplierRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierService implements CreateSupplierUseCase {

    private final SupplierRepositoryPort supplierRepositoryPort;

    @Override
    public Result<Supplier> execute(Supplier supplier) {
        Supplier saved = supplierRepositoryPort.save(supplier);
        return Result.ok(saved);
    }
}
