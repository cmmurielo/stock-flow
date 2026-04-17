package com.inventario.stock_flow.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductSupplier {
    private final Long id;
    private final Product product;
    private final Supplier supplier;
    private BigDecimal purchaseCost;
    private LocalDateTime lastUpdate;

    public void updateCost(BigDecimal newCost) {
        if (newCost == null || newCost.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El costo de compra no puede ser negativo");
        }
        this.purchaseCost = newCost;
        this.lastUpdate = LocalDateTime.now();
    }
}
