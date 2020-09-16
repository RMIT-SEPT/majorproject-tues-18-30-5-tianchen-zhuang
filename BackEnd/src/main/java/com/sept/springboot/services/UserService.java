package com.sept.springboot.services;

import com.sept.springboot.exception.UserNotFoundException;
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
        try
        {
            return userRepository.save(user);
        }
        catch (Exception e)
        {
            throw new UserNotFoundException("User ID '" + user.getUserId() + "' already exists");
        }
    }

    public User findByUserId(long id)
    {
        User user = userRepository.findByUserId(id);

        if(user == null)
            throw new UserNotFoundException("User ID '" + id + "' does not exist");

        return user;
    }

    public User findByEmail(String email)
    {
        User user = userRepository.findByEmail(email);

        if(user == null)
            throw new UserNotFoundException("Email '" + email + "' does not exist");

        return user;
    }

    public Iterable<User> findAllUsers()
    {
        return userRepository.findAll();
    }

    public void deleteByUserId(long id)
    {
        User user = userRepository.findByUserId(id);

        if(user == null)
            throw new UserNotFoundException("Cannot delete user with ID '" + id + "'. This user does not exist");

        userRepository.delete(user);
    }
}
