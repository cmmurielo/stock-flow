package com.inventario.stock_flow.supplier.infrastructure.adapter.persistence.mapper;

import org.mapstruct.Mapper;

import com.inventario.stock_flow.supplier.domain.model.Supplier;
import com.inventario.stock_flow.supplier.infrastructure.adapter.persistence.entity.SupplierEntity;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierEntity toEntity(Supplier domain);

    Supplier toDomain(SupplierEntity entity);
}
