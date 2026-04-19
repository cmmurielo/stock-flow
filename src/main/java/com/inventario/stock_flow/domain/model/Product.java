package com.inventario.stock_flow.domain.model;

import java.math.BigDecimal;

import com.inventario.stock_flow.domain.core.result.DomainError;
import com.inventario.stock_flow.domain.core.result.Result;

public class Product {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;

    /** Constructor privado: usar {@link #create} para obtener un {@code Result<Product>}. */
    private Product(Long id, String name, String description, BigDecimal price, Integer stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    // ── Factory methods ───────────────────────────────────────────────────────

    /**
     * Reconstituye un {@code Product} desde la capa de persistencia, sin revalidar.
     * Solo para uso de mappers de infraestructura.
     */
    public static Product reconstitute(Long id, String name, String description,
                                        BigDecimal price, Integer stock) {
        return new Product(id, name, description, price, stock);
    }

    /**
     * Crea un {@code Product} validado.
     *
     * @return {@link Result.Success} con el producto, o {@link Result.Failure}
     *         con {@link DomainError.InvalidPrice} / {@link DomainError.InvalidStock}.
     */
    public static Result<Product> create(Long id, String name, String description,
                                         BigDecimal price, Integer stock) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            return Result.fail(new DomainError.InvalidPrice("El precio debe ser mayor o igual a 0"));
        }
        if (stock == null || stock < 0) {
            return Result.fail(new DomainError.InvalidStock("El stock inicial no puede ser negativo"));
        }
        return Result.ok(new Product(id, name, description, price, stock));
    }

    // ── Comportamiento de negocio ─────────────────────────────────────────────

    /**
     * Actualiza el precio del producto.
     *
     * @return {@link Result.Success} vacío, o {@link Result.Failure} con {@link DomainError.InvalidPrice}.
     */
    public Result<Void> updatePrice(BigDecimal newPrice) {
        if (newPrice == null || newPrice.compareTo(BigDecimal.ZERO) < 0) {
            return Result.fail(new DomainError.InvalidPrice("El precio debe ser mayor o igual a 0"));
        }
        this.price = newPrice;
        return Result.ok(null);
    }

    /**
     * Agrega stock al producto.
     *
     * @return {@link Result.Success} vacío, o {@link Result.Failure} con {@link DomainError.InvalidStock}.
     */
    public Result<Void> addStock(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            return Result.fail(new DomainError.InvalidStock("La cantidad a agregar debe ser mayor a 0"));
        }
        this.stock += quantity;
        return Result.ok(null);
    }

    /**
     * Reduce el stock del producto.
     *
     * @return {@link Result.Success} vacío, o {@link Result.Failure} con {@link DomainError.InvalidStock}.
     */
    public Result<Void> removeStock(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            return Result.fail(new DomainError.InvalidStock("La cantidad a retirar debe ser mayor a 0"));
        }
        if (this.stock - quantity < 0) {
            return Result.fail(new DomainError.InvalidStock("No hay suficiente stock disponible"));
        }
        this.stock -= quantity;
        return Result.ok(null);
    }

    // ── Getters y Setters ─────────────────────────────────────────────────────

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }

    public Integer getStock() { return stock; }
}
