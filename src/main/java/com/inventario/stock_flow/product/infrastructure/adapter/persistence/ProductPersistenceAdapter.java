package com.inventario.stock_flow.product.infrastructure.adapter.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.inventario.stock_flow.product.domain.model.Product;
import com.inventario.stock_flow.product.domain.ports.ProductRepositoryPort;
import com.inventario.stock_flow.product.infrastructure.adapter.persistence.entity.ProductEntity;
import com.inventario.stock_flow.product.infrastructure.adapter.persistence.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductRepositoryPort {

    private final JpaProductRepository jpaProductRepository;

    @Override
    public Product save(Product product) {
        ProductEntity entity = ProductMapper.toEntity(product);
        ProductEntity savedEntity = jpaProductRepository.save(entity);
        return ProductMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return jpaProductRepository.findById(id)
                .map(ProductMapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        List<ProductEntity> listEntity = jpaProductRepository.findAll();
        return listEntity.stream().map(ProductMapper::toDomain).toList();
    }

}
