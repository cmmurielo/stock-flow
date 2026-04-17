package com.inventario.stock_flow.infrastructure.rest.dto;

public record SupplierResponse(
        Long id,
        String name,
        String contactEmail,
        String address) {

}
