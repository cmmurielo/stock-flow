package com.inventario.stock_flow.supplier.application.service;

import org.springframework.stereotype.Service;

import com.inventario.stock_flow.supplier.application.usecase.CreateSupplierUseCase;
import com.inventario.stock_flow.supplier.domain.model.Supplier;
import com.inventario.stock_flow.supplier.domain.ports.SupplierRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierService implements CreateSupplierUseCase {

    private final SupplierRepositoryPort supplierRepositoryPort;

    @Override
    public Supplier execute(Supplier supplier) {
        return supplierRepositoryPort.save(supplier);
    }

}
