package com.sept.springboot.controller;

import com.sept.springboot.model.Business;
import com.sept.springboot.model.User;
import com.sept.springboot.services.BusinessService;
import com.sept.springboot.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return new ResponseEntity<Business>(newBusiness, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBusinessById(@PathVariable long id)
    {
        Business business = businessService.findByBusinessId(id);

        return new ResponseEntity<Business>(business, HttpStatus.OK);
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

        return new ResponseEntity<String>("Business with ID: '" + id + "' was deleted", HttpStatus.OK);
    }
}
