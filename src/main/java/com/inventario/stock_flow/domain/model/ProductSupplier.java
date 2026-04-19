package com.inventario.stock_flow.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.inventario.stock_flow.domain.core.result.DomainError;
import com.inventario.stock_flow.domain.core.result.Result;

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

    public Result<Void> updateCost(BigDecimal newCost) {
        if (newCost == null || newCost.compareTo(BigDecimal.ZERO) < 0) {
            return Result.fail(new DomainError.InvalidCost(
                    "El costo de compra no puede ser negativo"));
        }
        this.purchaseCost = newCost;
        this.lastUpdate = LocalDateTime.now();
        return Result.ok(null);
    }
}
