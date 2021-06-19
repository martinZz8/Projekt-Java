package com.example.backend.repositories;

import com.example.backend.model.ShoppingList;
import com.example.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
    //@Query("SELECT sl FROM shopping_list sl WHERE sl.user.id=?1")
    //List<ShoppingList> getAllByUserId(Long user_id);
}
