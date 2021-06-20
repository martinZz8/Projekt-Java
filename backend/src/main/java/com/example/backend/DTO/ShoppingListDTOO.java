package com.example.backend.DTO;

import com.example.backend.model.ShoppingList;

public class ShoppingListDTOO {
    private Long id;
    private String name;
    private UserDTOO user;

    public ShoppingListDTOO() {
    }

    public ShoppingListDTOO(Long id, String name, UserDTOO user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

    public ShoppingListDTOO(String name, UserDTOO user) {
        this.name = name;
        this.user = user;
    }

    public ShoppingListDTOO(ShoppingListDTOO shoppingListDTOO) {
        this.id = shoppingListDTOO.id;
        this.name = shoppingListDTOO.name;
        this.user = shoppingListDTOO.user;
    }

    public ShoppingListDTOO(ShoppingList shoppingList) {
        this.id = shoppingList.getId();
        this.name = shoppingList.getName();
        this.user = new UserDTOO(shoppingList.getUser());
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

    public UserDTOO getUser() {
        return user;
    }

    public void setUser(UserDTOO user) {
        this.user = user;
    }
}
