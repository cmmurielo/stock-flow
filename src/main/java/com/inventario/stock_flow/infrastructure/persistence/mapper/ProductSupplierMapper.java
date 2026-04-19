package com.inventario.stock_flow.infrastructure.persistence.mapper;

import com.inventario.stock_flow.domain.model.ProductSupplier;
import com.inventario.stock_flow.infrastructure.persistence.entity.ProductSupplierEntity;

/**
 * Mapper manual para {@link ProductSupplier} ↔ {@link ProductSupplierEntity}.
 */
public class ProductSupplierMapper {

    public static ProductSupplierEntity toEntity(ProductSupplier domain) {
        if (domain == null) return null;

        return new ProductSupplierEntity(
                domain.getId(),
                ProductMapper.toEntity(domain.getProduct()),
                SupplierMapper.toEntity(domain.getSupplier()),
                domain.getPurchaseCost(),
                domain.getLastUpdate());
    }

    public static ProductSupplier toDomain(ProductSupplierEntity entity) {
        if (entity == null) return null;

        return new ProductSupplier(
                entity.getId(),
                ProductMapper.toDomain(entity.getProduct()),
                SupplierMapper.toDomain(entity.getSupplier()),
                entity.getPurchaseCost(),
                entity.getLastUpdate());
    }
}
