package com.inventario.stock_flow.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;

import com.inventario.stock_flow.domain.model.ProductSupplier;
import com.inventario.stock_flow.infrastructure.persistence.entity.ProductSupplierEntity;

@Mapper(componentModel = "spring", uses = { ProductMapper.class, SupplierMapper.class })
public interface ProductSupplierMapper {

    ProductSupplierEntity toEntity(ProductSupplier domain);

    ProductSupplier toDomain(ProductSupplierEntity entity);

}
