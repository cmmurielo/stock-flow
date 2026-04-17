package com.inventario.stock_flow.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import com.inventario.stock_flow.infrastructure.persistence.repository.JpaSupplierRepository;
import org.springframework.stereotype.Component;

import com.inventario.stock_flow.domain.model.Supplier;
import com.inventario.stock_flow.domain.ports.SupplierRepositoryPort;
import com.inventario.stock_flow.infrastructure.persistence.mapper.SupplierMapper;

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
    public Optional<Supplier> findById(Long id) {
        return jpaRepository.findById(id).map(supplierMapper::toDomain);
    }

    @Override
    public List<Supplier> findAll() {
        return jpaRepository.findAll().stream()
                .map(supplierMapper::toDomain)
                .toList();
    }

}
