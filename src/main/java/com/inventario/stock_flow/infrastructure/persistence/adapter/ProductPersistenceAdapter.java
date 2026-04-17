package com.inventario.stock_flow.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import com.inventario.stock_flow.infrastructure.persistence.repository.JpaProductRepository;
import org.springframework.stereotype.Component;

import com.inventario.stock_flow.domain.model.Product;
import com.inventario.stock_flow.domain.ports.ProductRepositoryPort;
import com.inventario.stock_flow.infrastructure.persistence.entity.ProductEntity;
import com.inventario.stock_flow.infrastructure.persistence.mapper.ProductMapper;

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
    public Optional<Product> findById(Long id) {
        return jpaProductRepository.findById(id)
                .map(ProductMapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        List<ProductEntity> listEntity = jpaProductRepository.findAll();
        return listEntity.stream().map(ProductMapper::toDomain).toList();
    }

}
