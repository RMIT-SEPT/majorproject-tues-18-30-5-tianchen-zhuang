package com.sept.springboot.controller;

import com.sept.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.sept.springboot.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<User> createNewUser(@RequestBody User user)
    {
        User user1 = userService.saveOrUpdateUser(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
}
