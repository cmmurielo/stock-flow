package com.inventario.stock_flow.inventory.infrastructure.rest.dto;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record AssociationRequest(
        @NotNull(message = "El ID del producto es obligatorio") UUID productId,

        @NotNull(message = "El ID del proveedor es obligatorio") UUID supplierId,

        @NotNull(message = "El costo de compra es obligatorio") @DecimalMin(value = "0.0", inclusive = true, message = "El costo no puede ser negativo") BigDecimal cost) {

}
