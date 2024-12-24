package com.project.fooddeliveryapp.service;

import com.project.fooddeliveryapp.model.User;
import com.project.fooddeliveryapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Value("#{new java.text.SimpleDateFormat('yyyy-MM-dd').format(new java.util.Date())}")
    private String currentDate;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
