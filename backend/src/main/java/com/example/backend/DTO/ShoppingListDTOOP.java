package com.example.backend.DTO;

public class ShoppingListDTOOP {
    private Long id;
    private String name;

    public ShoppingListDTOOP(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ShoppingListDTOOP(String name) {
        this.name = name;
    }

    public ShoppingListDTOOP(ShoppingListDTOOP shoppingListDTOOP) {
        this.id = shoppingListDTOOP.id;
        this.name = shoppingListDTOOP.name;
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
}
