package com.example.backend.controller;

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
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{userId}", produces = "application/json")
    public Optional<User> getUser(@PathVariable String userId)
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

    @PostMapping(path= "/add")
    public ResponseEntity<String> addUser(@RequestBody User newUser)
    {
        if(userService.addUser(newUser)!=null)
            return ResponseEntity.ok("Success");
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure");
    }

    @DeleteMapping(path="/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId)
    {
        try
        {
            if(userService.deleteUser(Long.parseLong(userId)))
                return ResponseEntity.ok("Success");
            else
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure");
        }
        catch(NumberFormatException e)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure");
        }
    }

    @PutMapping(path="/update")
    public User updateUser(@RequestBody User newUser)
    {
        return userService.updateUser(newUser);
    }
}
