package com.inventario.stock_flow.domain.model;

import java.util.regex.Pattern;

import com.inventario.stock_flow.domain.core.result.DomainError;
import com.inventario.stock_flow.domain.core.result.Result;

public class Supplier {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
                    Pattern.CASE_INSENSITIVE);

    private Long id;
    private String name;
    private String contactEmail;
    private String address;

    /** Constructor privado: usar {@link #create} para obtener un {@code Result<Supplier>}. */
    private Supplier(Long id, String name, String contactEmail, String address) {
        this.id = id;
        this.name = name;
        this.contactEmail = contactEmail;
        this.address = address;
    }

    // ── Factory methods ───────────────────────────────────────────────────────

    /**
     * Reconstituye un {@code Supplier} desde la capa de persistencia, sin revalidar el email.
     * Solo para uso de mappers de infraestructura.
     */
    public static Supplier reconstitute(Long id, String name, String contactEmail, String address) {
        return new Supplier(id, name, contactEmail, address);
    }

    /**
     * Crea un {@code Supplier} validado.
     *
     * @return {@link Result.Success} con el proveedor, o {@link Result.Failure}
     *         con {@link DomainError.InvalidEmail} si el email no es válido.
     */
    public static Result<Supplier> create(Long id, String name, String contactEmail, String address) {
        if (contactEmail == null || !EMAIL_PATTERN.matcher(contactEmail).matches()) {
            return Result.fail(new DomainError.InvalidEmail(
                    "El formato del email del proveedor no es válido"));
        }
        return Result.ok(new Supplier(id, name, contactEmail, address));
    }

    // ── Getters y Setters ─────────────────────────────────────────────────────

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getContactEmail() { return contactEmail; }

    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }
}
