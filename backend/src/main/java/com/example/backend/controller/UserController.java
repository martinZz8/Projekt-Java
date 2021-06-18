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
    public Optional<User> updateUser(@RequestBody User newUser)
    {
        User u = userService.updateUser(newUser);
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
}
