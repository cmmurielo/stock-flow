package com.inventario.stock_flow.infrastructure.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.stock_flow.application.usecase.AssociateSupplierUseCase;
import com.inventario.stock_flow.domain.core.result.DomainError;
import com.inventario.stock_flow.domain.core.result.Result;
import com.inventario.stock_flow.domain.model.ProductSupplier;
import com.inventario.stock_flow.infrastructure.rest.dto.AssociationRequest;
import com.inventario.stock_flow.infrastructure.rest.dto.ProductSupplierResponse;
import com.inventario.stock_flow.infrastructure.rest.exception.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Validated
public class InventoryController {

    private final AssociateSupplierUseCase associateSupplierUseCase;

    @PostMapping("/associate")
    public ResponseEntity<?> associate(
            @Valid @RequestBody AssociationRequest request,
            HttpServletRequest httpRequest) {

        Result<ProductSupplier> result = associateSupplierUseCase.execute(
                request.productId(),
                request.supplierId(),
                request.cost());

        return switch (result) {
            case Result.Success<ProductSupplier> s -> {
                ProductSupplier ps = s.value();
                yield ResponseEntity.status(HttpStatus.CREATED).body(
                        new ProductSupplierResponse(
                                ps.getId(),
                                ps.getProduct().getName(),
                                ps.getSupplier().getName(),
                                ps.getPurchaseCost(),
                                ps.getLastUpdate()));
            }
            case Result.Failure<ProductSupplier> f -> mapError(f.error(), httpRequest.getRequestURI());
        };
    }

    private ResponseEntity<ErrorResponse> mapError(DomainError error, String path) {
        return switch (error) {
            case DomainError.ProductNotFound e -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Not Found", e.message(), path));
            case DomainError.SupplierNotFound e -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "Not Found", e.message(), path));
            case DomainError.InvalidCost e -> ResponseEntity.badRequest()
                    .body(new ErrorResponse(400, "Domain Error", e.message(), path));
            default -> ResponseEntity.internalServerError()
                    .body(new ErrorResponse(500, "Internal Error", error.message(), path));
        };
    }
}
