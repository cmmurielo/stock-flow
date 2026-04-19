package com.inventario.stock_flow.application.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.inventario.stock_flow.application.usecase.AssociateSupplierUseCase;
import com.inventario.stock_flow.domain.core.result.DomainError;
import com.inventario.stock_flow.domain.core.result.Result;
import com.inventario.stock_flow.domain.model.Product;
import com.inventario.stock_flow.domain.model.ProductSupplier;
import com.inventario.stock_flow.domain.model.Supplier;
import com.inventario.stock_flow.domain.ports.ProductRepositoryPort;
import com.inventario.stock_flow.domain.ports.ProductSupplierRepositoryPort;
import com.inventario.stock_flow.domain.ports.SupplierRepositoryPort;

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
    public Result<ProductSupplier> execute(Long productId, Long supplierId, BigDecimal cost) {
        // Buscar producto — retorna Failure si no existe
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return Result.fail(new DomainError.ProductNotFound(productId));
        }

        // Buscar proveedor — retorna Failure si no existe
        Supplier supplier = supplierRepository.findById(supplierId).orElse(null);
        if (supplier == null) {
            return Result.fail(new DomainError.SupplierNotFound(supplierId));
        }

        // Actualizar vínculo existente o crear uno nuevo
        return productSupplierRepository
                .findByProductIdAndSupplierId(productId, supplierId)
                .map(existingLink -> {
                    Result<Void> updateResult = existingLink.updateCost(cost);
                    if (updateResult instanceof Result.Failure<Void> f) {
                        return Result.<ProductSupplier>fail(f.error());
                    }
                    return Result.ok(productSupplierRepository.save(existingLink));
                })
                .orElseGet(() -> {
                    ProductSupplier newLink = new ProductSupplier(
                            null, product, supplier, cost, LocalDateTime.now());
                    return Result.ok(productSupplierRepository.save(newLink));
                });
    }
}
