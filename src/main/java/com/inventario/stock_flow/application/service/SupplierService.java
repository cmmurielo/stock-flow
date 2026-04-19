package com.inventario.stock_flow.application.service;

import org.springframework.stereotype.Service;

import com.inventario.stock_flow.application.usecase.CreateSupplierUseCase;
import com.inventario.stock_flow.domain.core.result.DomainError;
import com.inventario.stock_flow.domain.core.result.Result;
import com.inventario.stock_flow.domain.model.Supplier;
import com.inventario.stock_flow.domain.ports.SupplierRepositoryPort;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierService implements CreateSupplierUseCase {

    private final SupplierRepositoryPort supplierRepositoryPort;

    @Override
    @Transactional
    public Result<Supplier> execute(Supplier supplier) {
        if (supplierRepositoryPort.existsByDocument(supplier.getDocument())) {
            return Result.fail(new DomainError.SupplierAlreadyExists(supplier.getDocument()));
        }

        Supplier saved = supplierRepositoryPort.save(supplier);
        return Result.ok(saved);
    }
}
