package com.example.backend.service;

import com.example.backend.DTO.ShoppingListDTOI;
import com.example.backend.DTO.ShoppingListDTOOP;
import com.example.backend.model.ShoppingList;
import com.example.backend.model.User;
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

    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository, UserRepository userRepository)
    {
        this.shoppingListRepository = shoppingListRepository;
        this.userRepository = userRepository;
    }

    public List<ShoppingList> getAllShoppingLists() {
        return shoppingListRepository.findAll();
    }

    public List<ShoppingListDTOOP> getAllShoppingListsSmall() {
        List<ShoppingList> sl = shoppingListRepository.findAll();
        List<ShoppingListDTOOP> ret_sl = new ArrayList<>();
        for(ShoppingList item : sl)
        {
            ShoppingListDTOOP n_sl = new ShoppingListDTOOP(item.getId(), item.getName());
            ret_sl.add(n_sl);
        }
        return ret_sl;
    }

    public Optional<ShoppingList> getShoppingList(Long shoppingListId) {
        return shoppingListRepository.findById(shoppingListId);
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
            List<ShoppingList> s_u = optional_sl.get().getUser().getShoppingList();
            for(ShoppingList item : s_u)
            {
                if(item.getId().equals(shoppingListId))
                {
                    s_u.remove(item);
                    break;
                }
            }
            shoppingListRepository.deleteById(shoppingListId);
            return true;
        }
        else
        {
            return false;
        }
    }

    public ShoppingList updateShoppingList(Long shoppingListId, ShoppingListDTOI newShoppingList) {
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

                return shoppingListRepository.save(sl);
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
