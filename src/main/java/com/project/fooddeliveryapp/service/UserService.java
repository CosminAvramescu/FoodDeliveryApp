package com.project.fooddeliveryapp.service;

import com.project.fooddeliveryapp.model.users.Users;
import com.project.fooddeliveryapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Users findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
