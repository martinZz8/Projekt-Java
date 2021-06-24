package com.example.backend.controller;

import com.example.backend.DTO.ProductsInListsDTOI;
import com.example.backend.DTO.ProductsInListsDTOO;
import com.example.backend.model.ProductsInLists;
import com.example.backend.service.ProductsInListsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="products_in_lists")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductsInListsController {
    private final ProductsInListsService productsInListsService;

    @Autowired
    public ProductsInListsController(ProductsInListsService productsInListsService)
    {
        this.productsInListsService = productsInListsService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<ProductsInListsDTOO> getAllProductsInLists()
    {
        return productsInListsService.getAllProductsInLists();
    }

    @GetMapping(path = "/{productsInListsId}", produces = "application/json")
    public Optional<ProductsInListsDTOO> getProductInList(@PathVariable String productsInListsId)
    {
        try
        {
            return productsInListsService.getProductInList(Long.parseLong(productsInListsId));
        }
        catch(NumberFormatException e)
        {
            return Optional.empty();
        }
    }

    @PostMapping(path= "/add")
    public ResponseEntity<String> addProductsInList(@RequestBody ProductsInListsDTOI newProductsInLists)
    {
        Integer ret = productsInListsService.addProductsInList(newProductsInLists);
        if(ret==1)
            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure - given data is invalid");
    }

    @PutMapping(path = "/update/{productsInListsId}")
    public ResponseEntity<String> updateQuantity(@PathVariable String productsInListsId, @RequestParam(name = "quantity", required = true) String quantity)
    {
        try
        {
            Integer ret = productsInListsService.updateQuantity(Long.parseLong(productsInListsId), Integer.parseInt(quantity));
            if(ret==1)
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            else
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure - products_in_lists with given ID doesn't exist");
        }
        catch(NumberFormatException e)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure");
        }
    }

    @DeleteMapping(path = "/delete/{productsInListsId}")
    public ResponseEntity<String> deleteProductInList(@PathVariable String productsInListsId)
    {
        try
        {
            if(productsInListsService.deleteProductInList(Long.parseLong(productsInListsId)))
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            else
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure -mhm");
        }
        catch(NumberFormatException e)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure");
        }
    }

}
