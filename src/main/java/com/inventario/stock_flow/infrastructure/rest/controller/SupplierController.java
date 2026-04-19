package com.inventario.stock_flow.infrastructure.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.stock_flow.application.usecase.CreateSupplierUseCase;
import com.inventario.stock_flow.domain.core.result.DomainError;
import com.inventario.stock_flow.domain.core.result.Result;
import com.inventario.stock_flow.domain.model.Supplier;
import com.inventario.stock_flow.infrastructure.rest.dto.SupplierRequest;
import com.inventario.stock_flow.infrastructure.rest.dto.SupplierResponse;
import com.inventario.stock_flow.infrastructure.rest.exception.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
@Validated
public class SupplierController {

    private final CreateSupplierUseCase createSupplierUseCase;

    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody SupplierRequest request,
            HttpServletRequest httpRequest) {

        // 1. Construir y validar el modelo de dominio
        Result<Supplier> domainResult = Supplier.create(
                null,
                request.name(),
                request.contactEmail(),
                request.address());

        if (domainResult instanceof Result.Failure<Supplier> f) {
            return mapError(f.error(), httpRequest.getRequestURI());
        }

        // 2. Ejecutar el caso de uso (persistencia)
        Supplier supplierToSave = ((Result.Success<Supplier>) domainResult).value();
        Result<Supplier> saveResult = createSupplierUseCase.execute(supplierToSave);

        // 3. Pattern matching sobre el resultado final
        return switch (saveResult) {
            case Result.Success<Supplier> s -> {
                Supplier saved = s.value();
                yield ResponseEntity.status(HttpStatus.CREATED).body(
                        new SupplierResponse(
                                saved.getId(),
                                saved.getName(),
                                saved.getContactEmail(),
                                saved.getAddress()));
            }
            case Result.Failure<Supplier> f -> mapError(f.error(), httpRequest.getRequestURI());
        };
    }

    // ── Helper ────────────────────────────────────────────────────────────────

    private ResponseEntity<ErrorResponse> mapError(DomainError error, String path) {
        return switch (error) {
            case DomainError.InvalidEmail e -> ResponseEntity.badRequest()
                    .body(new ErrorResponse(400, "Domain Error", e.message(), path));
            default -> ResponseEntity.internalServerError()
                    .body(new ErrorResponse(500, "Internal Error", error.message(), path));
        };
    }
}
