package com.inventario.stock_flow.domain.core.result;

/**
 * Tipo suma que representa el resultado de una operación de dominio.
 * Solo puede ser {@link Success} (éxito con valor) o {@link Failure} (fallo con error tipado).
 * <p>
 * Uso:
 * <pre>{@code
 * Result<Product> result = Product.create(name, price, stock);
 * switch (result) {
 *     case Result.Success<Product> s -> // usar s.value()
 *     case Result.Failure<Product> f -> // usar f.error()
 * }
 * }</pre>
 */
public sealed interface Result<T> permits Result.Success, Result.Failure {

    /**
     * Resultado exitoso que contiene el valor producido.
     */
    record Success<T>(T value) implements Result<T> {}

    /**
     * Resultado fallido que contiene el error de dominio ocurrido.
     */
    record Failure<T>(DomainError error) implements Result<T> {}

    // ── Factory methods ──────────────────────────────────────────────────────

    static <T> Result<T> ok(T value) {
        return new Success<>(value);
    }

    static <T> Result<T> fail(DomainError error) {
        return new Failure<>(error);
    }

    // ── Helpers ──────────────────────────────────────────────────────────────

    default boolean isSuccess() {
        return this instanceof Success<T>;
    }

    default boolean isFailure() {
        return this instanceof Failure<T>;
    }
}
