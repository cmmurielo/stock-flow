package com.inventario.stock_flow.application.service;

import org.springframework.stereotype.Service;

import com.inventario.stock_flow.application.usecase.CreateSupplierUseCase;
import com.inventario.stock_flow.domain.model.Supplier;
import com.inventario.stock_flow.domain.ports.SupplierRepositoryPort;

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
