package com.example.backend.service;

import com.example.backend.DTO.ProductsInListsDTOI;
import com.example.backend.DTO.ProductsInListsDTOO;
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
import java.util.List;
import java.util.Optional;

@Service
public class ProductsInListsService {
    private final ProductsInListsRepository productsInListsRepository;
    private final ShoppingListRepository shoppingListRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductsInListsService(ProductsInListsRepository productsInListsRepository, ShoppingListRepository shoppingListRepository, ProductRepository productRepository)
    {
        this.productsInListsRepository = productsInListsRepository;
        this.shoppingListRepository = shoppingListRepository;
        this.productRepository = productRepository;
    }

    public List<ProductsInListsDTOO> getAllProductsInLists() {
        List<ProductsInLists> act_pInL = productsInListsRepository.findAll();
        List<ProductsInListsDTOO> ret_pInL = new ArrayList<>();
        for(ProductsInLists item : act_pInL)
        {
            ProductsInListsDTOO pInL = new ProductsInListsDTOO(item);
            ret_pInL.add(pInL);
        }
        return ret_pInL;
    }

    public Optional<ProductsInListsDTOO> getProductInList(Long productsInListsId) {
        Optional<ProductsInLists> optional_act_pInL = productsInListsRepository.findById(productsInListsId);
        if(optional_act_pInL.isPresent())
        {
            ProductsInListsDTOO ret_pInL = new ProductsInListsDTOO(optional_act_pInL.get());
            return Optional.of(ret_pInL);
        }
        else
        {
            return Optional.empty();
        }
    }

    @Transactional
    public ProductsInListsDTOO addProductsInList(ProductsInListsDTOI newProductsInLists) {
        Optional<ShoppingList> optional_sl = shoppingListRepository.findById(newProductsInLists.getShopping_list_id());
        Optional<Product> optional_p = productRepository.findById(newProductsInLists.getProduct_id());
        if(optional_sl.isPresent() && optional_p.isPresent())
        {
            ShoppingList sl = optional_sl.get();
            Product p = optional_p.get();
            ProductsInLists pInL = new ProductsInLists(sl, p, newProductsInLists.getQuantity());
            sl.getProductsInLists().add(pInL);
            p.getProductsInLists().add(pInL);
            ProductsInLists saved_pInL = productsInListsRepository.save(pInL);
            return new ProductsInListsDTOO(saved_pInL);
        }
        else
        {
            return null;
        }
    }

    public Integer updateQuantity(Long productsInListsId, Integer quantity) {
        Optional<ProductsInLists> optional_pInL = productsInListsRepository.findById(productsInListsId);
        if(optional_pInL.isPresent())
        {
            ProductsInLists pInL = optional_pInL.get();
            Integer q = pInL.getQuantity();
            if(!q.equals(quantity))
                pInL.setQuantity(quantity);

            productsInListsRepository.save(pInL);
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public boolean deleteProductInList(Long productsInListsId) {
        Optional<ProductsInLists> optional_pInL = productsInListsRepository.findById(productsInListsId);
        if(optional_pInL.isPresent())
        {
            ProductsInLists pInL = optional_pInL.get();
            //delete item in ShoppingList
            pInL.getShopping_list().getProductsInLists().remove(pInL);
            //delete item in Product
            pInL.getProduct().getProductsInLists().remove(pInL);
            //delete ProductsInList
            productsInListsRepository.deleteById(pInL.getId());
            return true;
        }
        else
        {
            return false;
        }
    }
}
