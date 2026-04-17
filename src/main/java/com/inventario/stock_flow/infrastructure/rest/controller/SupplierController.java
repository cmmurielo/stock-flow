package com.inventario.stock_flow.infrastructure.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.stock_flow.application.usecase.CreateSupplierUseCase;
import com.inventario.stock_flow.domain.model.Supplier;
import com.inventario.stock_flow.infrastructure.rest.dto.SupplierRequest;
import com.inventario.stock_flow.infrastructure.rest.dto.SupplierResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
@Validated
public class SupplierController {
    private final CreateSupplierUseCase createSupplierUseCase;

    @PostMapping
    public ResponseEntity<SupplierResponse> postMethodName(@Valid @RequestBody SupplierRequest request) {

        Supplier supplierToCreate = new Supplier(
                null,
                request.name(),
                request.contactEmail(),
                request.address());

        Supplier savedSupplier = createSupplierUseCase.execute(supplierToCreate);

        SupplierResponse response = new SupplierResponse(
                savedSupplier.getId(),
                savedSupplier.getName(),
                savedSupplier.getContactEmail(),
                savedSupplier.getAddress());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
