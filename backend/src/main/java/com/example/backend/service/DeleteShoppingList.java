package com.example.backend.service;

import com.example.backend.model.Product;
import com.example.backend.model.ProductsInLists;
import com.example.backend.model.ShoppingList;
import com.example.backend.repositories.ProductsInListsRepository;
import com.example.backend.repositories.ShoppingListRepository;

import java.util.Iterator;
import java.util.List;

public class DeleteShoppingList {

    public static void deleteShoppingList(ShoppingList sl, ProductsInListsRepository productsInListsRepository, ShoppingListRepository shoppingListRepository) {
        Long shoppingListId = sl.getId();
        Iterator<ProductsInLists> it = sl.getProductsInLists().iterator();
        while(it.hasNext())
        {
            //remove productsInLists in Product
            ProductsInLists pInL = it.next();
            Product p = pInL.getProduct();
            p.getProductsInLists().remove(pInL);
            //remove products in lists in ShoppingList
            //sl.getProductsInLists().remove(pInL); <- FORBIDDEN USAGE
            it.remove(); //<- PROPER USAGE
            //delete ProductsInLists
            productsInListsRepository.deleteById(pInL.getId());
        }
        //delete shopping list in ShoppingList
        if(shoppingListRepository.findById(shoppingListId).isPresent())
        {
            shoppingListRepository.deleteById(shoppingListId);
        }
    }
}
