package com.inventario.stock_flow.infrastructure.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SupplierRequest(
        @NotNull(message = "el documento del proveedor es obligatorio") Long document,

        @NotBlank(message = "El nombre del proveedor es obligatorio") String name,

        @NotBlank(message = "El email es obligatorio") @Email(message = "Formato de email inválido") String contactEmail,

        String address) {
}
