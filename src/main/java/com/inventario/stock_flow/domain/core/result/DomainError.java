package com.inventario.stock_flow.domain.core.result;

/**
 * Jerarquía sellada de errores de dominio del sistema Stock-Flow.
 * <p>
 * Cada subtipo es un {@code record} inmutable que encapsula los datos
 * relevantes del error. Usar en combinación con {@link Result.Failure}.
 * <p>
 * El consumidor puede distinguir el tipo exacto con pattern matching:
 * <pre>{@code
 * switch (error) {
 *     case DomainError.InvalidPrice e    -> ...
 *     case DomainError.ProductNotFound e -> ...
 *     // ...
 * }
 * }</pre>
 */
public sealed interface DomainError
        permits DomainError.InvalidPrice,
                DomainError.InvalidStock,
                DomainError.InvalidEmail,
                DomainError.InvalidCost,
                DomainError.ProductNotFound,
                DomainError.SupplierNotFound {

    /** Mensaje descriptivo del error para el consumidor. */
    String message();

    // ── Errores de Producto ───────────────────────────────────────────────────

    /** El precio proporcionado es nulo o negativo. */
    record InvalidPrice(String message) implements DomainError {}

    /** El stock inicial proporcionado es nulo o negativo. */
    record InvalidStock(String message) implements DomainError {}

    // ── Errores de Proveedor ──────────────────────────────────────────────────

    /** El email de contacto del proveedor no tiene un formato válido. */
    record InvalidEmail(String message) implements DomainError {}

    // ── Errores de Asociación Producto-Proveedor ──────────────────────────────

    /** El costo de compra proporcionado es nulo o negativo. */
    record InvalidCost(String message) implements DomainError {}

    // ── Errores de Búsqueda ───────────────────────────────────────────────────

    /** No se encontró un producto con el ID indicado. */
    record ProductNotFound(Long id) implements DomainError {
        @Override
        public String message() {
            return "Producto no encontrado con ID: " + id;
        }
    }

    /** No se encontró un proveedor con el ID indicado. */
    record SupplierNotFound(Long id) implements DomainError {
        @Override
        public String message() {
            return "Proveedor no encontrado con ID: " + id;
        }
    }
}
