package com.inventario.stock_flow.product.infrastructure.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.stock_flow.product.application.usecase.CreateProductUseCase;
import com.inventario.stock_flow.product.domain.model.Product;
import com.inventario.stock_flow.product.infrastructure.rest.dto.ProductRequest;
import com.inventario.stock_flow.product.infrastructure.rest.dto.ProductResponse;

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
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
        Product productToCreate = new Product(
                null,
                request.name(),
                request.description(),
                request.price(),
                request.stock());

        Product savedProduct = createProductUseCase.execute(productToCreate);

        ProductResponse reponse = new ProductResponse(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getDescription(),
                savedProduct.getPrice(),
                savedProduct.getStock());

        return ResponseEntity.status(HttpStatus.CREATED).body(reponse);
    }

}
