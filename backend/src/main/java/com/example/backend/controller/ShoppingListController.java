package com.example.backend.controller;

import com.example.backend.DTO.ShoppingListDTOI;
import com.example.backend.model.ShoppingList;
import com.example.backend.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="shopping_list")
public class ShoppingListController {
    private final ShoppingListService shoppingListService;

    @Autowired
    public ShoppingListController(ShoppingListService shoppingListService)
    {
        this.shoppingListService = shoppingListService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<ShoppingList> getAllShoppingLists()
    {
        return shoppingListService.getAllShoppingLists();
    }

    @GetMapping(path = "/{shoppingListId}", produces = "application/json")
    public Optional<ShoppingList> getShoppingList(@PathVariable String shoppingListId)
    {
        try
        {
            return shoppingListService.getShoppingList(Long.parseLong(shoppingListId));
        }
        catch(NumberFormatException e)
        {
            return Optional.empty();
        }
    }

    @PostMapping(path= "/add")
    public ResponseEntity<String> addShoppingList(@RequestBody ShoppingListDTOI newShoppingList)
    {
        Integer ret = shoppingListService.addShoppingList(newShoppingList);
        if(ret==1)
            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure - user with given ID doesn't exist");
    }

    @DeleteMapping(path="/delete/{shoppingListId}")
    public ResponseEntity<String> deleteShoppingList(@PathVariable String shoppingListId)
    {
        try
        {
            if(shoppingListService.deleteShoppingList(Long.parseLong(shoppingListId)))
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            else
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure");
        }
        catch(NumberFormatException e)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure");
        }
    }

    @PutMapping(path="/update/{shoppingListId}", produces = "application/json")
    public Optional<ShoppingList> updateShoppingList(@PathVariable String shoppingListId, @RequestBody ShoppingListDTOI newShoppingList)
    {
        try
        {
            ShoppingList sl = shoppingListService.updateShoppingList(Long.parseLong(shoppingListId), newShoppingList);
            if(sl!=null)
            {
                return Optional.of(sl);
            }
            else
            {
                return Optional.empty();
            }
        }
        catch(NumberFormatException e)
        {
            return Optional.empty();
        }
    }
}
