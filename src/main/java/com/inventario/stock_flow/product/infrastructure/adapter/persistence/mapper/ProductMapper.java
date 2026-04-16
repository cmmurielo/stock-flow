package com.inventario.stock_flow.product.infrastructure.adapter.persistence.mapper;

import com.inventario.stock_flow.product.domain.model.Product;
import com.inventario.stock_flow.product.infrastructure.adapter.persistence.entity.ProductEntity;

public class ProductMapper {

    public static ProductEntity toEntity(Product domain) {
        if (domain == null)
            return null;

        return new ProductEntity(
                domain.getId(),
                domain.getName(),
                domain.getDescription(),
                domain.getPrice(),
                domain.getStock());
    }

    public static Product toDomain(ProductEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStock());
    }

}
