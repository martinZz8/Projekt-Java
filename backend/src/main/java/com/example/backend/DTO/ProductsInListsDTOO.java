package com.example.backend.DTO;

import com.example.backend.model.ProductsInLists;

public class ProductsInListsDTOO {
    private Long id;
    private Long shopping_list_id;
    private Long product_id;
    private Integer quantity;

    public ProductsInListsDTOO() {
    }

    public ProductsInListsDTOO(Long id, Long shopping_list_id, Long product_id, Integer quantity) {
        this.id = id;
        this.shopping_list_id = shopping_list_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public ProductsInListsDTOO(ProductsInListsDTOO productsInListsDTOO) {
        this.id = productsInListsDTOO.id;
        this.shopping_list_id = productsInListsDTOO.shopping_list_id;
        this.product_id = productsInListsDTOO.product_id;
        this.quantity = productsInListsDTOO.quantity;
    }

    public ProductsInListsDTOO(ProductsInLists productsInLists) {
        this.id = productsInLists.getId();
        this.shopping_list_id = productsInLists.getShopping_list().getId();
        this.product_id = productsInLists.getProduct().getId();
        this.quantity = productsInLists.getQuantity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopping_list_id() {
        return shopping_list_id;
    }

    public void setShopping_list_id(Long shopping_list_id) {
        this.shopping_list_id = shopping_list_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
