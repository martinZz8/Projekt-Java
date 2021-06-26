package com.example.backend.controller;

import com.example.backend.DTO.ProductDTOOPT;
import com.example.backend.DTO.ShoppingListDTOI;
import com.example.backend.DTO.ShoppingListDTOO;
import com.example.backend.DTO.ShoppingListDTOOP;
import com.example.backend.model.ShoppingList;
import com.example.backend.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="shopping_list")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ShoppingListController {
    private final ShoppingListService shoppingListService;

    @Autowired
    public ShoppingListController(ShoppingListService shoppingListService)
    {
        this.shoppingListService = shoppingListService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<ShoppingListDTOO> getAllShoppingLists()
    {
        return shoppingListService.getAllShoppingLists();
    }

    @GetMapping(path = "/all/small", produces = "application/json")
    public List<ShoppingListDTOOP> getAllShoppingListsSmall()
    {
        return shoppingListService.getAllShoppingListsSmall();
    }

    @GetMapping(path = "/{shoppingListId}", produces = "application/json")
    public Optional<ShoppingListDTOO> getShoppingList(@PathVariable String shoppingListId)
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

    @GetMapping(path = "/all_products/{shoppingListId}", produces = "application/json")
    public List<ProductDTOOPT> getAllProducts(@PathVariable String shoppingListId)
    {
        try
        {
            return shoppingListService.getAllProducts(Long.parseLong(shoppingListId));
        }
        catch(NumberFormatException e)
        {
            return new ArrayList<>();
        }
    }

    @PostMapping(path= "/add")
    public ResponseEntity<ShoppingListDTOOP> addShoppingList(@RequestBody ShoppingListDTOI newShoppingList)
    {
        ShoppingListDTOOP ret_s = shoppingListService.addShoppingList(newShoppingList);
        if(ret_s!=null)
            return ResponseEntity.status(HttpStatus.CREATED).body(ret_s);
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
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
    public Optional<ShoppingListDTOO> updateShoppingList(@PathVariable String shoppingListId, @RequestBody ShoppingListDTOI newShoppingList)
    {
        try
        {
            ShoppingListDTOO sl = shoppingListService.updateShoppingList(Long.parseLong(shoppingListId), newShoppingList);
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
