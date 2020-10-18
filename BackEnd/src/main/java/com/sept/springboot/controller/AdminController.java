package com.sept.springboot.controller;

import com.sept.springboot.services.MapValidationErrorService;
import com.sept.springboot.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.sept.springboot.model.Admin;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/*
    API that controls all functionality for the admins.
 */
@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController
{
    @Autowired
    private AdminService adminService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    /*
        Creates a new admin into the database.
     */
    @PostMapping("")
    public ResponseEntity<?> createNewAdmin(@Valid @RequestBody Admin admin, BindingResult result)
    {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap != null)
            return errorMap;

        Admin newAdmin = adminService.addOrUpdateAdmin(admin);

        return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
    }

    /*
        Gets an admin with the id (adminId) passed through
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable long id)
    {
        return new ResponseEntity<>(adminService.findByAdminId(id), HttpStatus.OK);
    }

    /*
        Returns the admin with the email that is passed through.
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getAdminByEmail(@PathVariable String email)
    {
        Admin admin = adminService.findByEmail(email);

        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    /*
        Returns the password of the admin with the email that is passed through.
        This is an unsecure way to allow logging in a admin in the frontend.
        Is here as a temporary loose solution.
     */
    @GetMapping("/login/{email}")
    public ResponseEntity<?> getPasswordByEmail(@PathVariable String email)
    {
        Admin admin = adminService.findByEmail(email);

        return new ResponseEntity<>(admin.getPassword(), HttpStatus.OK);
    }

    /*
        Returns all admins in the database.
        Returned as an iterable list.
     */
    @GetMapping("/all")
    public Iterable<Admin> getAllAdmins()
    {
        return adminService.findAllAdmins();
    }

    /*
        Deletes the admin with the id (adminId) that is passed through.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable long id)
    {
        adminService.deleteByAdminId(id);

        return new ResponseEntity<>("Admin with ID: '" + id + "' was deleted", HttpStatus.OK);
    }

    /*
        Updates the admin with the id (adminId) passed through.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable(value = "id") long id, @Valid @RequestBody Admin adminDetails, BindingResult result)
    {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap != null)
            return errorMap;

        Admin admin = adminService.findByAdminId(id);

        admin.setUsername(adminDetails.getUsername());
        admin.setPassword(adminDetails.getPassword());
        admin.setEmail(adminDetails.getEmail());

        adminService.addOrUpdateAdmin(admin);

        return new ResponseEntity<>("Admin with ID: '" + id + "' has been updated", HttpStatus.OK);
    }
}
