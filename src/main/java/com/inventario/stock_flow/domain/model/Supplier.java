package com.inventario.stock_flow.domain.model;

import java.util.regex.Pattern;

import com.inventario.stock_flow.domain.core.result.DomainError;
import com.inventario.stock_flow.domain.core.result.Result;

public class Supplier {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    private Long id;
    private Long document;
    private String name;
    private String contactEmail;
    private String address;

    private Supplier(Long id, Long document, String name, String contactEmail, String address) {
        this.id = id;
        this.document = document;
        this.name = name;
        this.contactEmail = contactEmail;
        this.address = address;
    }

    public static Supplier reconstitute(Long id, Long document, String name, String contactEmail, String address) {
        return new Supplier(id, document, name, contactEmail, address);
    }

    public static Result<Supplier> create(Long id, Long document, String name, String contactEmail, String address) {
        if (contactEmail == null || !EMAIL_PATTERN.matcher(contactEmail).matches()) {
            return Result.fail(new DomainError.InvalidEmail(
                    "El formato del email del proveedor no es válido"));
        }
        return Result.ok(new Supplier(id, document, name, contactEmail, address));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getDocument() {
        return document;
    }

    public void setDocument(Long document) {
        this.document = document;
    }
}
