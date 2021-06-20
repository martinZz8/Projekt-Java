package com.example.backend.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "product")
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", columnDefinition = "Decimal(10,2) default '0.00'")
    private Double price;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<ProductsInLists> productsInListsList;

    public Product() {
    }

    public Product(Long id, String name, Double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.productsInListsList = new ArrayList<>();
    }

    public Product(String name, Double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.productsInListsList = new ArrayList<>();
    }

    public Product(Product new_product) {
        this.id = new_product.id;
        this.name = new_product.name;
        this.price = new_product.price;
        this.description = new_product.description;
        this.productsInListsList = new_product.productsInListsList;
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

    public List<ProductsInLists> getProductsInListsList() {
        return productsInListsList;
    }

    public void setProductsInListsList(List<ProductsInLists> productsInListsList) {
        this.productsInListsList = productsInListsList;
    }
}
