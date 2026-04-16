package com.inventario.stock_flow.supplier.infrastructure.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SupplierRequest(
        @NotBlank(message = "El nombre del proveedor es obligatorio") String name,

        @NotBlank(message = "El email es obligatorio") @Email(message = "Formato de email inválido") String contactEmail,

        String address) {

}
