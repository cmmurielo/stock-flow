package com.inventario.stock_flow.inventory.infrastructure.adapter.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.inventario.stock_flow.inventory.domain.model.ProductSupplier;
import com.inventario.stock_flow.inventory.domain.ports.ProductSupplierRepositoryPort;
import com.inventario.stock_flow.inventory.infrastructure.adapter.persistence.entity.ProductSupplierEntity;
import com.inventario.stock_flow.inventory.infrastructure.adapter.persistence.mapper.ProductSupplierMapper;
import com.inventario.stock_flow.inventory.infrastructure.adapter.persistence.repository.JpaProductSupplierRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductSupplierPersistenceAdapter implements ProductSupplierRepositoryPort {

    private final JpaProductSupplierRepository jpaRepository;
    private final ProductSupplierMapper mapper;

    @Override
    public ProductSupplier save(ProductSupplier productSupplier) {
        ProductSupplierEntity entity = mapper.toEntity(productSupplier);
        ProductSupplierEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public List<ProductSupplier> findByProductId(UUID productId) {
        return jpaRepository.findByProductId(productId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<ProductSupplier> findByProductIdAndSupplierId(UUID productId, UUID supplierId) {
        return jpaRepository.findByProductIdAndSupplierId(productId, supplierId)
                .map(mapper::toDomain);
    }

}
