package com.inventario.stock_flow.infrastructure.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.stock_flow.application.usecase.CreateProductUseCase;
import com.inventario.stock_flow.domain.core.result.DomainError;
import com.inventario.stock_flow.domain.core.result.Result;
import com.inventario.stock_flow.domain.model.Product;
import com.inventario.stock_flow.infrastructure.rest.dto.ProductRequest;
import com.inventario.stock_flow.infrastructure.rest.dto.ProductResponse;
import com.inventario.stock_flow.infrastructure.rest.exception.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final CreateProductUseCase createProductUseCase;

    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody ProductRequest request,
            HttpServletRequest httpRequest) {

        Result<Product> domainResult = Product.create(
                null,
                request.name(),
                request.description(),
                request.price(),
                request.stock());

        if (domainResult instanceof Result.Failure<Product>(DomainError error)) {
            return mapError(error, httpRequest.getRequestURI());
        }

        Product productToSave = ((Result.Success<Product>) domainResult).value();
        Result<Product> saveResult = createProductUseCase.execute(productToSave);

        return switch (saveResult) {
            case Result.Success<Product> s -> {
                Product saved = s.value();
                yield ResponseEntity.status(HttpStatus.CREATED).body(
                        new ProductResponse(
                                saved.getId(),
                                saved.getName(),
                                saved.getDescription(),
                                saved.getPrice(),
                                saved.getStock()));
            }
            case Result.Failure<Product> f -> mapError(f.error(), httpRequest.getRequestURI());
        };
    }

    private ResponseEntity<ErrorResponse> mapError(DomainError error, String path) {
        return switch (error) {
            case DomainError.InvalidPrice e -> ResponseEntity.badRequest()
                    .body(new ErrorResponse(400, "Domain Error", e.message(), path));
            case DomainError.InvalidStock e -> ResponseEntity.badRequest()
                    .body(new ErrorResponse(400, "Domain Error", e.message(), path));
            default -> ResponseEntity.internalServerError()
                    .body(new ErrorResponse(500, "Internal Error", error.message(), path));
        };
    }
}
