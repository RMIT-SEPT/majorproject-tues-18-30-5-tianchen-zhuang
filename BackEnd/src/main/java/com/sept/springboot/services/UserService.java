package com.sept.springboot.services;

import com.sept.springboot.model.User;
import com.sept.springboot.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    public User saveOrUpdateUser(User user)
    {
        // Business logic
        return userRepository.save(user);
    }
}
