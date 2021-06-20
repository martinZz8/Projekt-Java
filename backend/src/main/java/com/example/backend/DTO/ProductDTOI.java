package com.example.backend.DTO;

public class ProductDTOI {
    private String name;
    private Double price;
    private String description;

    public ProductDTOI() {
    }

    public ProductDTOI(String name, Double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public ProductDTOI(ProductDTOI productDTOI) {
        this.name = productDTOI.name;
        this.price = productDTOI.price;
        this.description = productDTOI.description;
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
