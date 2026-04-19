package com.inventario.stock_flow.domain.core.result;

public sealed interface DomainError
        permits DomainError.InvalidPrice,
        DomainError.InvalidStock,
        DomainError.InvalidEmail,
        DomainError.InvalidCost,
        DomainError.ProductNotFound,
        DomainError.SupplierNotFound,
        DomainError.SupplierAlreadyExists {

    String message();

    record InvalidPrice(String message) implements DomainError {
    }

    record InvalidStock(String message) implements DomainError {
    }

    record InvalidEmail(String message) implements DomainError {
    }

    record InvalidCost(String message) implements DomainError {
    }

    record ProductNotFound(Long id) implements DomainError {
        @Override
        public String message() {
            return "Producto no encontrado con ID: " + id;
        }
    }

    record SupplierNotFound(Long id) implements DomainError {
        @Override
        public String message() {
            return "Proveedor no encontrado con ID: " + id;
        }
    }
    record SupplierAlreadyExists(Long documento) implements DomainError {
        @Override
        public String message() {
            return "Ya existe un proveedor con el documento: " + documento;
        }
    }
}
