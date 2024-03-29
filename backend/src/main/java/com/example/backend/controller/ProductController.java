package com.example.backend.controller;

import com.example.backend.DTO.ProductDTOI;
import com.example.backend.DTO.ProductDTOOP;
import com.example.backend.DTO.ShoppingListDTOI;
import com.example.backend.model.Product;
import com.example.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<ProductDTOOP> getAllProducts()
    {
        return productService.getAllProducts();
    }

    @GetMapping(path = "/{productId}", produces = "application/json")
    public Optional<ProductDTOOP> getProduct(@PathVariable String productId)
    {   try
        {
            return productService.getProduct(Long.parseLong(productId));
        }
        catch(NumberFormatException e)
        {
            return Optional.empty();
        }
    }

    @PostMapping(path= "/add")
    public ResponseEntity<ProductDTOOP> addProduct(@RequestBody ProductDTOI newProduct)
    {
        ProductDTOOP ret_p = productService.addProduct(newProduct);
        if(ret_p!=null)
            return ResponseEntity.status(HttpStatus.CREATED).body(ret_p);
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @DeleteMapping(path="/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId)
    {
        try
        {
            if(productService.deleteProduct(Long.parseLong(productId)))
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            else
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure");
        }
        catch(NumberFormatException e)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure");
        }
    }

    @PutMapping(path="/update/{productId}", produces = "application/json")
    public Optional<ProductDTOOP> updateProduct(@PathVariable String productId, @RequestBody ProductDTOI newProduct)
    {
        try
        {
            ProductDTOOP p = productService.updateProduct(Long.parseLong(productId), newProduct);
            if(p!=null)
            {
                return Optional.of(p);
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
