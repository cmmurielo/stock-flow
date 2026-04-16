package com.inventario.stock_flow.product.infrastructure.rest.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(
        @NotBlank(message = "El nombre es obligatorio") String name,
        String description,
        @NotNull(message = "El precio no puede ser nulo") @DecimalMin(value = "0.0", inclusive = true, message = "El precio debe ser mayor o igual a 0") BigDecimal price,
        @NotNull(message = "El stock no puede ser nulo") @Min(value = 0, message = "El stock no puede ser negativo") Integer stock) {
}
