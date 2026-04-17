package com.inventario.stock_flow.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;

import com.inventario.stock_flow.domain.model.Supplier;
import com.inventario.stock_flow.infrastructure.persistence.entity.SupplierEntity;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierEntity toEntity(Supplier domain);

    Supplier toDomain(SupplierEntity entity);
}
