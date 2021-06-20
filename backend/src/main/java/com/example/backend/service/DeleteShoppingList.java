package com.example.backend.service;

import com.example.backend.model.Product;
import com.example.backend.model.ProductsInLists;
import com.example.backend.repositories.ProductsInListsRepository;
import com.example.backend.repositories.ShoppingListRepository;

import java.util.Iterator;
import java.util.List;

public class DeleteShoppingList {

    public static void deleteShoppingList(Long shoppingListId, List<ProductsInLists> actProdInLists, ProductsInListsRepository productsInListsRepository, ShoppingListRepository shoppingListRepository) {
        Iterator<ProductsInLists> it = actProdInLists.iterator();
        while(it.hasNext())
        {
            //remove productsInLists in Product
            ProductsInLists pInL = it.next();
            Product p = pInL.getProduct();
            p.getProductsInListsList().remove(pInL);
            //remove products in lists in ShoppingList
            actProdInLists.remove(pInL);
            //delete ProductsInLists
            productsInListsRepository.deleteById(pInL.getId());
        }
        //delete shopping list in ShoppingList
        shoppingListRepository.deleteById(shoppingListId);
    }
}
