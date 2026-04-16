package com.inventario.stock_flow.inventory.infrastructure.rest.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record AssociationRequest(
        @NotNull(message = "El ID del producto es obligatorio") Long productId,

        @NotNull(message = "El ID del proveedor es obligatorio") Long supplierId,

        @NotNull(message = "El costo de compra es obligatorio") @DecimalMin(value = "0.0", inclusive = true, message = "El costo no puede ser negativo") BigDecimal cost) {

}
