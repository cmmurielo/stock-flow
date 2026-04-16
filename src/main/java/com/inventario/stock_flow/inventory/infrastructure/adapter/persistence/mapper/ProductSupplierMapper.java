package com.inventario.stock_flow.inventory.infrastructure.adapter.persistence.mapper;

import org.mapstruct.Mapper;

import com.inventario.stock_flow.inventory.domain.model.ProductSupplier;
import com.inventario.stock_flow.inventory.infrastructure.adapter.persistence.entity.ProductSupplierEntity;
import com.inventario.stock_flow.product.infrastructure.adapter.persistence.mapper.ProductMapper;
import com.inventario.stock_flow.supplier.infrastructure.adapter.persistence.mapper.SupplierMapper;

@Mapper(componentModel = "spring", uses = { ProductMapper.class, SupplierMapper.class })
public interface ProductSupplierMapper {

    ProductSupplierEntity toEntity(ProductSupplier domain);

    ProductSupplier toDomain(ProductSupplierEntity entity);

}
