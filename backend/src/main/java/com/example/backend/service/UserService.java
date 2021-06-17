package com.example.backend.service;

import java.util.List;
import java.util.Optional;

import com.example.backend.model.User;
import com.example.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long userId)
    {
        return userRepository.findById(userId);
    }

    public User addUser(User newUser)
    {
        User u = userRepository.save(newUser);
        return u;
    }

    public boolean deleteUser(Long userId) {
        if(userRepository.existsById(userId))
        {
            userRepository.deleteById(userId);
            return true;
        }
        else
        {
            return false;
        }
    }

    public User updateUser(User u) {
        User user = userRepository.getByEmail(u.getEmail());
        if(user!=null)
        {
            user.setFirst_name(u.getFirst_name());
            user.setLast_name(u.getLast_name());
            user.setBlocked(u.getBlocked());
            user.setDob(u.getDob());
            return userRepository.save(user);
        }
        else
        {
            return null;
        }
    }
}
