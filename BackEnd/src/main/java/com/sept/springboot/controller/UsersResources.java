package com.sept.springboot.controller;

import com.sept.springboot.model.Users;

import com.sept.springboot.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/rest/users")
public class UsersResources {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping(value = "/all")
    public List<Users> getAll() {
        return usersRepository.findAll();
    }
    
    @PostMapping(value = "/load")
    public List<Users> persist(@RequestBody final Users users) {
        usersRepository.save(users);
        return usersRepository.findAll();
    }
    
    @PostMapping(value = "/authenticate", produces = "application/json;charset=UTF-8")
    public List<Users> findUser(){
    	
		return null;
    }

}