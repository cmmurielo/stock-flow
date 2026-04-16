package com.inventario.stock_flow.supplier.infrastructure.adapter.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.inventario.stock_flow.supplier.domain.model.Supplier;
import com.inventario.stock_flow.supplier.domain.ports.SupplierRepositoryPort;
import com.inventario.stock_flow.supplier.infrastructure.adapter.persistence.mapper.SupplierMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SupplierPersistenceAdapter implements SupplierRepositoryPort {
    private final JpaSupplierRepository jpaRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public Supplier save(Supplier supplier) {
        return supplierMapper.toDomain(jpaRepository.save(supplierMapper.toEntity(supplier)));
    }

    @Override
    public Optional<Supplier> findById(UUID id) {
        return jpaRepository.findById(id).map(supplierMapper::toDomain);
    }

    @Override
    public List<Supplier> findAll() {
        return jpaRepository.findAll().stream()
                .map(supplierMapper::toDomain)
                .toList();
    }

}
