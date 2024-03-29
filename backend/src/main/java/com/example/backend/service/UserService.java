package com.example.backend.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.example.backend.DTO.*;
import com.example.backend.model.ShoppingList;
import com.example.backend.model.User;
import com.example.backend.repositories.ProductRepository;
import com.example.backend.repositories.ProductsInListsRepository;
import com.example.backend.repositories.ShoppingListRepository;
import com.example.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ShoppingListRepository shoppingListRepository;
    private final ProductRepository productRepository;
    private final ProductsInListsRepository productsInListsRepository;

    @Autowired
    public UserService(UserRepository userRepository, ShoppingListRepository shoppingListRepository, ProductRepository productRepository, ProductsInListsRepository productsInListsRepository) {
        this.userRepository = userRepository;
        this.shoppingListRepository = shoppingListRepository;
        this.productRepository = productRepository;
        this.productsInListsRepository = productsInListsRepository;
    }

    public List<UserDTOO> getAllUsers()
    {
        List<UserDTOO> proper_users = new ArrayList<>();
        List<User> actual_users = userRepository.findAll();
        for (User u: actual_users) {
            UserDTOO ret_u = new UserDTOO(u);
            proper_users.add(ret_u);
        }
        return proper_users;
    }

    public Optional<UserDTOO> getUser(Long userId)
    {
        Optional<User> actual_user = userRepository.findById(userId);
        if(actual_user.isPresent())
        {
            UserDTOO ret_u = new UserDTOO(actual_user.get());
            return Optional.of(ret_u);
        }
        else
        {
            return Optional.empty();
        }
    }

    public List<ShoppingListDTOOP> getAllShoppingLists(String email) {
        User user = userRepository.getByEmail(email);
        if(user!=null)
        {
            List<ShoppingList> sl = user.getShoppingList();
            List<ShoppingListDTOOP> ret_sl = new ArrayList<>();
            for(ShoppingList item : sl)
            {
                ShoppingListDTOOP n_sl = new ShoppingListDTOOP(item);
                ret_sl.add(n_sl);
            }
            return ret_sl;
        }
        else
        {
            return new ArrayList<>();
        }
    }

    public boolean addUser(UserDTOI newUser)
    {
        User user = userRepository.getByEmail(newUser.getEmail());
        if (user==null)
        {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encoded_password = passwordEncoder.encode(newUser.getPassword());
            User u = new User(newUser.getEmail(), newUser.getFirst_name(), newUser.getLast_name(), newUser.getDob(), encoded_password, newUser.getBlocked());
            userRepository.save(u);
            return true;
        }
        else
        {
            return false;
        }
    }

    @Transactional
    public boolean deleteUser(Long userId) {
        Optional<User> optional_u = userRepository.findById(userId);
        if(optional_u.isPresent())
        {
            User u = optional_u.get();
            //deleting all linked shopping lists
            List<ShoppingList> sl = u.getShoppingList();
            for(ShoppingList item : sl)
            {
                //deleting all linked entries in Product, ShoppingList and ProductsInLists
                DeleteShoppingList.deleteShoppingList(item, productsInListsRepository, shoppingListRepository);
            }
            //deleting user
            userRepository.deleteById(userId);
            //System.out.println("Proper deletion of User");
            return true;
        }
        else
        {
            return false;
        }
    }

    public UserDTOO updateUser(UserDTOI u) {
        User user = userRepository.getByEmail(u.getEmail());
        if(user!=null)
        {
            String f_n = u.getFirst_name();
            if(f_n.length()>0 && !f_n.equals(user.getFirst_name()))
                user.setFirst_name(f_n);

            String l_n = u.getLast_name();
            if(l_n.length()>0 && !l_n.equals(user.getLast_name()))
                user.setLast_name(l_n);

            Integer b = u.getBlocked();
            if(b != null && b>=0 && b<=1)
                user.setBlocked(b);

            user.setDob(u.getDob());

            return new UserDTOO(userRepository.save(user));
        }
        else
        {
            return null;
        }
    }

    public boolean updateBlockOfUser(String email, Integer block_flag) {
        User user = userRepository.getByEmail(email);
        if(user!=null)
        {
            if(block_flag>=0 && block_flag<=1)
            {
                user.setBlocked(block_flag);
                userRepository.save(user);
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public Integer updatePasswordOfUser(String email, String new_password) {
        User user = userRepository.getByEmail(email);
        if(user!=null)
        {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(!passwordEncoder.matches(new_password, user.getPass_hash()))
            {
                String np = passwordEncoder.encode(new_password);
                user.setPass_hash(np);
                userRepository.save(user);
                return 2;
            }
            else
            {
                return 1;
            }
        }
        else
        {
            return 0;
        }
    }

    public UserVerifyO postVerifyUser(UserVerifyDTOI userVerifyDTOI) {
        User u = userRepository.getByEmail(userVerifyDTOI.getEmail());
        if(u!=null)
        {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(passwordEncoder.matches(userVerifyDTOI.getPassword(), u.getPass_hash()))
            {
                if(u.getBlocked().equals(0))
                    return new UserVerifyO(2, u.getId(), "Success");
                else
                    return new UserVerifyO(1, 0L, "Failure - user is blocked");
            }
            else
            {
                return new UserVerifyO(0, 0L, "Failure - wrong input");
            }
        }
        else
        {
            return new UserVerifyO(0, 0L, "Failure - wrong input");
        }
    }
}
