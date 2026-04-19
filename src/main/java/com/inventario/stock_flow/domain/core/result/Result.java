package com.inventario.stock_flow.domain.core.result;

public sealed interface Result<T> permits Result.Success, Result.Failure {

    record Success<T>(T value) implements Result<T> {
    }

    record Failure<T>(DomainError error) implements Result<T> {
    }

    static <T> Result<T> ok(T value) {
        return new Success<>(value);
    }

    static <T> Result<T> fail(DomainError error) {
        return new Failure<>(error);
    }

    default boolean isSuccess() {
        return this instanceof Success<T>;
    }

    default boolean isFailure() {
        return this instanceof Failure<T>;
    }
}
