package com.example.backend.DTO;

public class ProductsInListsDTOI {
    private Long shopping_list_id;
    private Long product_id;
    private Integer quantity;

    public ProductsInListsDTOI() {
    }

    public ProductsInListsDTOI(Long shopping_list_id, Long product_id, Integer quantity) {
        this.shopping_list_id = shopping_list_id;
        this.product_id = product_id;
        this.quantity = quantity;
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
