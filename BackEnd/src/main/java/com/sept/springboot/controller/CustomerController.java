package com.sept.springboot.controller;

import com.sept.springboot.services.MapValidationErrorService;
import com.sept.springboot.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.sept.springboot.model.Customer;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin
public class CustomerController
{
    @Autowired
    private CustomerService customerService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewCustomer(@Valid @RequestBody Customer customer, BindingResult result)
    {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap != null)
            return errorMap;

        Customer newCustomer = customerService.addOrUpdateCustomer(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable long id)
    {
        Customer customer = customerService.findByCustomerId(id);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getCustomerEmail(@PathVariable String email)
    {
        Customer customer = customerService.findByEmail(email);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    // Temporary solution to login
    @GetMapping("/login/{email}")
    public ResponseEntity<?> getPasswordByEmail(@PathVariable String email)
    {
        Customer customer = customerService.findByEmail(email);

        return new ResponseEntity<>(customer.getPassword(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Customer> getAllCustomers()
    {
        return customerService.findAllCustomers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable long id)
    {
        customerService.deleteByCustomerId(id);

        return new ResponseEntity<>("Customer with ID: '" + id + "' was deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCutsomer(@PathVariable(value = "id") long id, @Valid @RequestBody Customer customerDetails, BindingResult result)
    {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap != null)
            return errorMap;

        Customer customer = customerService.findByCustomerId(id);

        customer.setUsername(customerDetails.getUsername());
        customer.setPassword(customerDetails.getPassword());
        customer.setEmail(customerDetails.getEmail());
        customer.setRoleID(customerDetails.getRoleID());

        customerService.addOrUpdateCustomer(customer);

        return new ResponseEntity<>("Customer with ID: '" + id + "' has been updated", HttpStatus.OK);
    }
}
