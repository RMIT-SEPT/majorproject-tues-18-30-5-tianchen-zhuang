package com.sept.springboot.controller;

import com.sept.springboot.services.MapValidationErrorService;
import com.sept.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.sept.springboot.model.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController
{
    @Autowired
    private UserService userService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewUser(@Valid @RequestBody User user, BindingResult result)
    {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap != null)
            return errorMap;

        User newUser = userService.saveOrUpdateUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id)
    {
        User user = userService.findByUserId(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserEmail(@PathVariable String email)
    {
        User user = userService.findByEmail(email);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<User> getAllUsers()
    {
        return userService.findAllUsers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id)
    {
        userService.deleteByUserId(id);

        return new ResponseEntity<>("User with ID: '" + id + "' was deleted", HttpStatus.OK);
    }
}
