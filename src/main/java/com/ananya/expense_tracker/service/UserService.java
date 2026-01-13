package com.ananya.expense_tracker.service;


import com.ananya.expense_tracker.entity.User;
import com.ananya.expense_tracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    // constructor injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register a new user
    public User register(User user) {
        // (you can later add check: if email already exists)
        return userRepository.save(user);
    }

    // Login: return user if email+password correct, else null
    public User login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
