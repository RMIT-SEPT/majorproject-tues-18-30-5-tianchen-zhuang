package com.sept.springboot.controller;

import com.sept.springboot.model.Business;
import com.sept.springboot.services.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @PostMapping("")
    public ResponseEntity<?> createNewBusiness(@Valid @RequestBody Business business, BindingResult result)
    {
        if(result.hasErrors())
        {
            Map<String,String> errorMap = new HashMap<>();
            for(FieldError error: result.getFieldErrors())
            {
                return new ResponseEntity<List<FieldError>>(result.getFieldErrors(), HttpStatus.BAD_REQUEST);
            }
        }

        Business business1 = businessService.saveOrUpdateBusiness(business);
        return new ResponseEntity<Business>(business, HttpStatus.CREATED);
    }


}
