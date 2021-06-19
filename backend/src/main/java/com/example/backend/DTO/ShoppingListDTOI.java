package com.example.backend.DTO;

public class ShoppingListDTOI {
    private String name;
    private Long user_id;

    public ShoppingListDTOI(String name, Long user_id) {
        this.name = name;
        this.user_id = user_id;
    }

    public ShoppingListDTOI(ShoppingListDTOI new_shopping_list) {
        this.name = new_shopping_list.name;
        this.user_id = new_shopping_list.user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
