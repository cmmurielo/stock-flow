package com.inventario.stock_flow.inventory.infrastructure.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.stock_flow.inventory.application.usecase.AssociateSupplierUseCase;
import com.inventario.stock_flow.inventory.domain.model.ProductSupplier;
import com.inventario.stock_flow.inventory.infrastructure.rest.dto.AssociationRequest;
import com.inventario.stock_flow.inventory.infrastructure.rest.dto.ProductSupplierResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Validated
public class InventoryController {

    private final AssociateSupplierUseCase associateSupplierUseCase;

    @PostMapping("/associate")
    public ResponseEntity<ProductSupplierResponse> associate(@Valid @RequestBody AssociationRequest request) {
        ProductSupplier result = associateSupplierUseCase.execute(request.productId(), request.supplierId(),
                request.cost());

        ProductSupplierResponse response = new ProductSupplierResponse(
                result.getId(),
                result.getProduct().getName(),
                result.getSupplier().getName(),
                result.getPurchaseCost(),
                result.getLastUpdate());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
