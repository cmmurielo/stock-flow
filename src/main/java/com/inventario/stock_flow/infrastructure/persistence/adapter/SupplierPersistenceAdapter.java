package com.inventario.stock_flow.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.inventario.stock_flow.domain.model.Supplier;
import com.inventario.stock_flow.domain.ports.SupplierRepositoryPort;
import com.inventario.stock_flow.infrastructure.persistence.entity.SupplierEntity;
import com.inventario.stock_flow.infrastructure.persistence.mapper.SupplierMapper;
import com.inventario.stock_flow.infrastructure.persistence.repository.JpaSupplierRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SupplierPersistenceAdapter implements SupplierRepositoryPort {

    private final JpaSupplierRepository jpaRepository;

    @Override
    public Supplier save(Supplier supplier) {
        SupplierEntity entity = SupplierMapper.toEntity(supplier);
        SupplierEntity saved = jpaRepository.save(entity);
        return SupplierMapper.toDomain(saved);
    }

    @Override
    public Optional<Supplier> findById(Long id) {
        return jpaRepository.findById(id).map(SupplierMapper::toDomain);
    }

    @Override
    public List<Supplier> findAll() {
        return jpaRepository.findAll().stream()
                .map(SupplierMapper::toDomain)
                .toList();
    }
}
