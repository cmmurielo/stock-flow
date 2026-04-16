package com.inventario.stock_flow.inventory.application.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.inventario.stock_flow.inventory.application.usecase.AssociateSupplierUseCase;
import com.inventario.stock_flow.inventory.domain.model.ProductSupplier;
import com.inventario.stock_flow.inventory.domain.ports.ProductSupplierRepositoryPort;
import com.inventario.stock_flow.product.domain.model.Product;
import com.inventario.stock_flow.product.domain.ports.ProductRepositoryPort;
import com.inventario.stock_flow.supplier.domain.model.Supplier;
import com.inventario.stock_flow.supplier.domain.ports.SupplierRepositoryPort;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductSupplierService implements AssociateSupplierUseCase {

    private final ProductRepositoryPort productRepository;
    private final SupplierRepositoryPort supplierRepository;
    private final ProductSupplierRepositoryPort productSupplierRepository;

    @Override
    @Transactional
    public ProductSupplier execute(UUID productId, UUID supplierId, BigDecimal cost) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + productId));

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado con ID: " + supplierId));

        return productSupplierRepository.findByProductIdAndSupplierId(productId, supplierId)
                .map(existingLink -> {
                    existingLink.updateCost(cost);
                    return productSupplierRepository.save(existingLink);
                })
                .orElseGet(() -> {
                    ProductSupplier newLink = new ProductSupplier(UUID.randomUUID(), product, supplier, cost,
                            LocalDateTime.now());
                    return productSupplierRepository.save(newLink);
                });

    }

}
