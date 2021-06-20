package com.example.backend.model;

import javax.persistence.*;

@Entity(name = "products_in_lists")
@Table(name= "products_in_lists")
public class ProductsInLists {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "shopping_list_id", nullable=false)
    private ShoppingList shopping_list;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable=false)
    private Product product;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public ProductsInLists() {
    }

    public ProductsInLists(Long id, ShoppingList shopping_list, Product product, Integer quantity) {
        this.id = id;
        this.shopping_list = shopping_list;
        this.product = product;
        this.quantity = quantity;
    }

    public ProductsInLists(ShoppingList shopping_list, Product product, Integer quantity) {
        this.shopping_list = shopping_list;
        this.product = product;
        this.quantity = quantity;
    }

    public ProductsInLists(ProductsInLists new_products_in_lists) {
        this.id = new_products_in_lists.id;
        this.shopping_list = new_products_in_lists.shopping_list;
        this.product = new_products_in_lists.product;
        this.quantity = new_products_in_lists.quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShoppingList getShopping_list() {
        return shopping_list;
    }

    public void setShopping_list(ShoppingList shopping_list) {
        this.shopping_list = shopping_list;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
