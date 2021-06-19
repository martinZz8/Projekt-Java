package com.example.backend.controller;

import com.example.backend.DTO.ShoppingListDTOOP;
import com.example.backend.DTO.UserDTOI;
import com.example.backend.DTO.UserDTOO;
import com.example.backend.model.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.model.User;
import com.example.backend.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<UserDTOO> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{userId}", produces = "application/json")
    public Optional<UserDTOO> getUser(@PathVariable String userId)
    {
        try
        {
            return userService.getUser(Long.parseLong(userId));
        }
        catch(NumberFormatException e)
        {
            return Optional.empty();
        }
    }

    @GetMapping(path="/all_shopping_lists/{email}", produces = "application/json")
    public List<ShoppingListDTOOP> getAllShoppingLists(@PathVariable String email)
    {
        return userService.getAllShoppingLists(email);
    }

    @PostMapping(path= "/add")
    public ResponseEntity<String> addUser(@RequestBody UserDTOI newUser)
    {
        if(userService.addUser(newUser))
            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure");
    }

    @DeleteMapping(path="/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId)
    {
        try
        {
            if(userService.deleteUser(Long.parseLong(userId)))
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            else
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure");
        }
        catch(NumberFormatException e)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure");
        }
    }

    @PutMapping(path="/update", produces = "application/json")
    public Optional<UserDTOO> updateUser(@RequestBody UserDTOI newUser)
    {
        UserDTOO u = userService.updateUser(newUser);
        if(u!=null)
        {
            return Optional.of(u);
        }
        else
        {
            return Optional.empty();
        }
    }

    @PutMapping(path="/set_block", produces = "application/json")
    public ResponseEntity<String> updateBlockOfUser(@RequestParam(name = "email", required = true) String email, @RequestParam(name="block", required = true) String block_flag)
    {
        if(userService.updateBlockOfUser(email, Integer.parseInt(block_flag)))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Success");
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure");
    }

    @PutMapping(path="/update_password", produces = "application/json")
    public ResponseEntity<String> updatePasswordOfUser(@RequestParam(name = "email", required = true) String email, @RequestParam(name="password", required = true) String new_password)
    {
        Integer ret = userService.updatePasswordOfUser(email, new_password);
        if(ret==2)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Success");
        else if (ret==1)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure - the same password was given");
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure - user with given email doesn't exist");
    }
}
