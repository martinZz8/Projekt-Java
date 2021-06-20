package com.example.backend.DTO;

import com.example.backend.model.Product;

public class ProductDTOOP {
    private Long id;
    private String name;
    private Double price;
    private String description;

    public ProductDTOOP() {
    }

    public ProductDTOOP(Long id, String name, Double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public ProductDTOOP(String name, Double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public ProductDTOOP(ProductDTOOP productDTOOP) {
        this.id = productDTOOP.id;
        this.name = productDTOOP.name;
        this.price = productDTOOP.price;
        this.description = productDTOOP.description;
    }

    public ProductDTOOP(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
