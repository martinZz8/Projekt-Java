package com.example.backend.service;

import com.example.backend.DTO.ProductDTOI;
import com.example.backend.DTO.ProductDTOOP;
import com.example.backend.model.Product;
import com.example.backend.model.ProductsInLists;
import com.example.backend.model.ShoppingList;
import com.example.backend.repositories.ProductRepository;
import com.example.backend.repositories.ProductsInListsRepository;
import com.example.backend.repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ShoppingListRepository shoppingListRepository;
    private final ProductsInListsRepository productsInListsRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ShoppingListRepository shoppingListRepository, ProductsInListsRepository productsInListsRepository)
    {
        this.productRepository = productRepository;
        this.shoppingListRepository = shoppingListRepository;
        this.productsInListsRepository = productsInListsRepository;
    }

    public List<ProductDTOOP> getAllProducts() {
        List<Product> act_p = productRepository.findAll();
        List<ProductDTOOP> ret_p = new ArrayList<>();
        for(Product item : act_p)
        {
            ProductDTOOP new_p = new ProductDTOOP(item);
            ret_p.add(new_p);
        }
        return ret_p;
    }

    public Optional<ProductDTOOP> getProduct(Long productId) {
        Optional<Product> act_p = productRepository.findById(productId);
        if(act_p.isPresent())
        {
            ProductDTOOP ret_p = new ProductDTOOP(act_p.get());
            return Optional.of(ret_p);
        }
        else
        {
            return Optional.empty();
        }
    }

    public boolean addProduct(ProductDTOI newProduct) {
        Product p = new Product(newProduct.getName(), newProduct.getPrice(), newProduct.getDescription());
        productRepository.save(p);
        return true;
    }

    @Transactional
    public boolean deleteProduct(Long productId) {
        Optional<Product> optional_p = productRepository.findById(productId);
        if(optional_p.isPresent())
        {
            List<ProductsInLists> pInL_list = optional_p.get().getProductsInListsList();
            Iterator<ProductsInLists> it = pInL_list.iterator();
            while(it.hasNext())
            {
                //remove productsInLists in ShoppingList
                ProductsInLists pInL = it.next();
                pInL.getShopping_list().getProductsInLists().remove(pInL);
                //remove productsInLists in Product
                pInL_list.remove(pInL);
                //delete ProductsInLists
                productsInListsRepository.deleteById(pInL.getId());
            }
            //delete product
            productRepository.deleteById(productId);
            return true;
        }
        else
        {
            return false;
        }
    }

    public ProductDTOOP updateProduct(Long productId, ProductDTOI newProduct) {
        Optional<Product> optional_p = productRepository.findById(productId);
        if(optional_p.isPresent())
        {
            Product p = optional_p.get();
            String name = newProduct.getName();
            if(!p.getName().equals(name))
                p.setName(name);

            Double price = newProduct.getPrice();
            if(!p.getPrice().equals(price))
                p.setPrice(price);

            String description = newProduct.getDescription();
            if(!p.getDescription().equals(description))
                p.setDescription(description);

            return new ProductDTOOP(productRepository.save(p));
        }
        else
        {
            return null;
        }
    }
}
