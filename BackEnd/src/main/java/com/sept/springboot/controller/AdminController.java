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

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController
{
    @Autowired
    private AdminService adminService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewAdmin(@Valid @RequestBody Admin admin, BindingResult result)
    {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap != null)
            return errorMap;

        Admin newAdmin = adminService.addOrUpdateAdmin(admin);

        return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable long id)
    {
        System.out.println("HERE IS THE ID: " + id);
        Admin admin = adminService.findByAdminId(id);

        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getAdminByEmail(@PathVariable String email)
    {
        Admin admin = adminService.findByEmail(email);

        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    // Temporary solution to login
    @GetMapping("/login/{email}")
    public ResponseEntity<?> getPasswordByEmail(@PathVariable String email)
    {
        Admin admin = adminService.findByEmail(email);

        return new ResponseEntity<>(admin.getPassword(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Admin> getAllAdmins()
    {
        return adminService.findAllAdmins();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable long id)
    {
        adminService.deleteByAdminId(id);

        return new ResponseEntity<>("Admin with ID: '" + id + "' was deleted", HttpStatus.OK);
    }

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
