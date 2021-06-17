package com.example.backend.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import com.example.backend.model.User;
import com.example.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers()
    {
        //return List.of(new User("test@gmail.com", "Maciej", "Harbuz", LocalDate.of(2000, Month.JANUARY, 4), "abc", 0));
        return userRepository.findAll();
    }
}
