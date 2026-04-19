package com.inventario.stock_flow.infrastructure.persistence.mapper;

import com.inventario.stock_flow.domain.model.Supplier;
import com.inventario.stock_flow.infrastructure.persistence.entity.SupplierEntity;

/**
 * Mapper manual para {@link Supplier} ↔ {@link SupplierEntity}.
 * Se usa {@link Supplier#reconstitute} para evitar re-ejecutar validaciones
 * de negocio sobre datos que ya fueron validados al persistirlos originalmente.
 */
public class SupplierMapper {

    public static SupplierEntity toEntity(Supplier domain) {
        if (domain == null) return null;

        return new SupplierEntity(
                domain.getId(),
                domain.getName(),
                domain.getContactEmail(),
                domain.getAddress());
    }

    public static Supplier toDomain(SupplierEntity entity) {
        if (entity == null) return null;

        return Supplier.reconstitute(
                entity.getId(),
                entity.getName(),
                entity.getContactEmail(),
                entity.getAddress());
    }
}
