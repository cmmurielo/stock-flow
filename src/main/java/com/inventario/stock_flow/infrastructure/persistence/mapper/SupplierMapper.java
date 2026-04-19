package com.inventario.stock_flow.infrastructure.persistence.mapper;

import com.inventario.stock_flow.domain.model.Supplier;
import com.inventario.stock_flow.infrastructure.persistence.entity.SupplierEntity;

public class SupplierMapper {

    public static SupplierEntity toEntity(Supplier domain) {
        if (domain == null)
            return null;

        return new SupplierEntity(
                domain.getId(),
                domain.getDocument(),
                domain.getName(),
                domain.getContactEmail(),
                domain.getAddress());
    }

    public static Supplier toDomain(SupplierEntity entity) {
        if (entity == null)
            return null;

        return Supplier.reconstitute(
                entity.getId(),
                entity.getDocument(),
                entity.getName(),
                entity.getContactEmail(),
                entity.getAddress());
    }
}
