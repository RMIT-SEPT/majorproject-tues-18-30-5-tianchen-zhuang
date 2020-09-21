package com.sept.springboot.controller;

import com.sept.springboot.model.Business;
import com.sept.springboot.services.BusinessService;
import com.sept.springboot.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/business")
@CrossOrigin
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewBusiness(@Valid @RequestBody Business business, BindingResult result)
    {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap != null)
            return errorMap;

        Business newBusiness = businessService.saveOrUpdateUser(business);
        return new ResponseEntity<>(newBusiness, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBusinessById(@PathVariable long id)
    {
        Business business = businessService.findByBusinessId(id);

        return new ResponseEntity<>(business, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getBusinessEmail(@PathVariable String email)
    {
        Business business = businessService.findByEmail(email);

        return new ResponseEntity<>(business, HttpStatus.OK);
    }

    // Temporary solution to login
    @GetMapping("/login/{email}")
    public ResponseEntity<?> getPasswordByEmail(@PathVariable String email)
    {
        Business business = businessService.findByEmail(email);

        return new ResponseEntity<>(business.getPassword(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Business> getAllBusinesses()
    {
        return businessService.findAllBusinesses();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBusiness(@PathVariable long id)
    {
        businessService.deleteByBusinessId(id);

        return new ResponseEntity<>("Business with ID: '" + id + "' was deleted", HttpStatus.OK);
    }
}
