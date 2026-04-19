package com.inventario.stock_flow.infrastructure.rest.dto;

public record SupplierResponse(
        Long id,
        Long document,
        String name,
        String contactEmail,
        String address) {

}
