package com.example.backend.service;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.backend.model.User;
import com.example.backend.repositories.UserRepository;
import net.bytebuddy.dynamic.DynamicType;
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
        List<User> proper_users = new ArrayList<>();
        List<User> actual_users = userRepository.findAll();
        for (User u: actual_users) {
            User ret_u = new User(u);
            ret_u.setPass_hash("");
            proper_users.add(ret_u);
        }
        return proper_users;
    }

    public Optional<User> getUser(Long userId)
    {
        Optional<User> actual_user = userRepository.findById(userId);
        if(actual_user.isPresent())
        {
            User ret_u = new User(actual_user.get());
            ret_u.setPass_hash("");
            return Optional.of(ret_u);
        }
        else
        {
            return Optional.empty();
        }
    }

    public boolean addUser(User newUser)
    {
        User user = userRepository.getByEmail(newUser.getEmail());
        if (user==null)
        {
            userRepository.save(newUser);
            return true;
        }
        else
        {
            return false;
        }
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

            User saved_user = new User(userRepository.save(user));
            saved_user.setPass_hash("");
            return saved_user;
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
}
