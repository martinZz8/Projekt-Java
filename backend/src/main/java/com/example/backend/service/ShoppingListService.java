package com.example.backend.service;

import com.example.backend.DTO.ProductDTOOPT;
import com.example.backend.DTO.ShoppingListDTOI;
import com.example.backend.DTO.ShoppingListDTOO;
import com.example.backend.DTO.ShoppingListDTOOP;
import com.example.backend.model.Product;
import com.example.backend.model.ProductsInLists;
import com.example.backend.model.ShoppingList;
import com.example.backend.model.User;
import com.example.backend.repositories.ProductRepository;
import com.example.backend.repositories.ProductsInListsRepository;
import com.example.backend.repositories.ShoppingListRepository;
import com.example.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;
    private final UserRepository userRepository;
    private final ProductsInListsRepository productsInListsRepository;

    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository, UserRepository userRepository, ProductRepository productRepository, ProductsInListsRepository productsInListsRepository)
    {
        this.shoppingListRepository = shoppingListRepository;
        this.userRepository = userRepository;
        this.productsInListsRepository = productsInListsRepository;
    }

    public List<ShoppingListDTOO> getAllShoppingLists() {
        List<ShoppingList> act_sl = shoppingListRepository.findAll();
        List<ShoppingListDTOO> ret_sl = new ArrayList<>();
        for(ShoppingList item : act_sl)
        {
            ShoppingListDTOO new_sl = new ShoppingListDTOO(item);
            ret_sl.add(new_sl);
        }
        return ret_sl;
    }

    public List<ShoppingListDTOOP> getAllShoppingListsSmall() {
        List<ShoppingList> act_sl = shoppingListRepository.findAll();
        List<ShoppingListDTOOP> ret_sl = new ArrayList<>();
        for(ShoppingList item : act_sl)
        {
            ShoppingListDTOOP new_sl = new ShoppingListDTOOP(item);
            ret_sl.add(new_sl);
        }
        return ret_sl;
    }

    public Optional<ShoppingListDTOO> getShoppingList(Long shoppingListId) {
        Optional<ShoppingList> optional_sl = shoppingListRepository.findById(shoppingListId);
        if(optional_sl.isPresent())
        {
            ShoppingListDTOO ret_sl = new ShoppingListDTOO(optional_sl.get());
            return Optional.of(ret_sl);
        }
        else
        {
            return Optional.empty();
        }
    }

    public List<ProductDTOOPT> getAllProducts(Long shoppingListId) {
        Optional<ShoppingList> optional_sl = shoppingListRepository.findById(shoppingListId);
        if(optional_sl.isPresent())
        {
            List<ProductsInLists> pInL_list = optional_sl.get().getProductsInLists();
            List<ProductDTOOPT> ret_products = new ArrayList<>();
            for(ProductsInLists pInL : pInL_list)
            {
                ProductDTOOPT ret_p = new ProductDTOOPT(pInL.getProduct(), pInL.getQuantity());
                ret_products.add(ret_p);
            }
            return ret_products;
        }
        else
        {
            return new ArrayList<>();
        }
    }

    public Integer addShoppingList(ShoppingListDTOI newShoppingList) {
        Optional<User> optional_u = userRepository.findById(newShoppingList.getUser_id());
        if(optional_u.isPresent())
        {
            ShoppingList sl = new ShoppingList(newShoppingList.getName(), optional_u.get());
            shoppingListRepository.save(sl);
            return 1;
        }
        else
        {
            return 0;
        }
    }

    @Transactional
    public boolean deleteShoppingList(Long shoppingListId) {
        Optional<ShoppingList> optional_sl = shoppingListRepository.findById(shoppingListId);
        if(optional_sl.isPresent())
        {
            //deleting shopping list in User
            List<ShoppingList> s_u = optional_sl.get().getUser().getShoppingList();
            for(ShoppingList item : s_u)
            {
                if(item.getId().equals(shoppingListId))
                {
                    s_u.remove(item);
                    break;
                }
            }
            //deleting all linked entries in Product, ShoppingList and ProductsInLists
            DeleteShoppingList.deleteShoppingList(shoppingListId, optional_sl.get().getProductsInLists(), productsInListsRepository, shoppingListRepository);
            return true;
        }
        else
        {
            return false;
        }
    }

    public ShoppingListDTOO updateShoppingList(Long shoppingListId, ShoppingListDTOI newShoppingList) {
        Optional<ShoppingList> optional_sl = shoppingListRepository.findById(shoppingListId);
        if(optional_sl.isPresent())
        {
            Optional<User> optional_u = userRepository.findById(newShoppingList.getUser_id());
            if(optional_u.isPresent())
            {
                ShoppingList sl = optional_sl.get();
                User u = optional_u.get();
                if(!sl.getUser().getId().equals(u.getId()))
                    sl.setUser(u);

                String n = newShoppingList.getName();
                if(!n.equals(sl.getName()))
                    sl.setName(n);

                return new ShoppingListDTOO(shoppingListRepository.save(sl));
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }
}
