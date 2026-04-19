package com.inventario.stock_flow.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.inventario.stock_flow.domain.model.ProductSupplier;
import com.inventario.stock_flow.domain.ports.ProductSupplierRepositoryPort;
import com.inventario.stock_flow.infrastructure.persistence.entity.ProductSupplierEntity;
import com.inventario.stock_flow.infrastructure.persistence.mapper.ProductSupplierMapper;
import com.inventario.stock_flow.infrastructure.persistence.repository.JpaProductSupplierRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductSupplierPersistenceAdapter implements ProductSupplierRepositoryPort {

    private final JpaProductSupplierRepository jpaRepository;

    @Override
    public ProductSupplier save(ProductSupplier productSupplier) {
        ProductSupplierEntity entity = ProductSupplierMapper.toEntity(productSupplier);
        ProductSupplierEntity saved = jpaRepository.save(entity);
        return ProductSupplierMapper.toDomain(saved);
    }

    @Override
    public List<ProductSupplier> findByProductId(Long productId) {
        return jpaRepository.findByProductId(productId).stream()
                .map(ProductSupplierMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<ProductSupplier> findByProductIdAndSupplierId(Long productId, Long supplierId) {
        return jpaRepository.findByProductIdAndSupplierId(productId, supplierId)
                .map(ProductSupplierMapper::toDomain);
    }
}
