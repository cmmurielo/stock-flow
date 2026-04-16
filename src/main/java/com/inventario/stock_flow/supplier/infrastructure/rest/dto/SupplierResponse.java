package com.inventario.stock_flow.supplier.infrastructure.rest.dto;

import java.util.UUID;

public record SupplierResponse(
        UUID id,
        String name,
        String contactEmail,
        String address) {

}
