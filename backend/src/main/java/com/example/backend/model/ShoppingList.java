package com.example.backend.model;

import javax.persistence.*;

@Entity(name = "shopping_list")
@Table(name = "shopping_list")
public class ShoppingList {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public ShoppingList() {
    }

    public ShoppingList(Long id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

    public ShoppingList(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public ShoppingList(ShoppingList copy_shopping_list)
    {
        this.id=copy_shopping_list.getId();
        this.name=copy_shopping_list.getName();
        this.user=copy_shopping_list.getUser();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
