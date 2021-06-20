package com.example.backend.DTO;

import com.example.backend.model.Product;

public class ProductDTOOPT {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private Integer quantity;

    public ProductDTOOPT() {
    }

    public ProductDTOOPT(Long id, String name, Double price, String description, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public ProductDTOOPT(String name, Double price, String description, Integer quantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public ProductDTOOPT(ProductDTOOPT productDTOOPT) {
        this.id = productDTOOPT.id;
        this.name = productDTOOPT.name;
        this.price = productDTOOPT.price;
        this.description = productDTOOPT.description;
        this.quantity = productDTOOPT.quantity;
    }

    public ProductDTOOPT(Product product, Integer quantity) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.quantity = quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

