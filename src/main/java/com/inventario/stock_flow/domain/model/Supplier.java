package com.inventario.stock_flow.domain.model;

import java.util.regex.Pattern;

public class Supplier {
    private Long id;
    private String name;
    private String contactEmail;
    private String address;

    public Supplier(Long id, String name, String contactEmail, String address) {
        this.id = id;
        this.name = name;
        validateEmail(contactEmail);
        this.contactEmail = contactEmail;
        this.address = address;
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

    private void validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        if (email == null || !pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("El formato del email del proveedor no es válido");
        }
    }
}
