package com.inventario.stock_flow.inventory.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.inventario.stock_flow.product.domain.model.Product;
import com.inventario.stock_flow.supplier.domain.model.Supplier;

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
