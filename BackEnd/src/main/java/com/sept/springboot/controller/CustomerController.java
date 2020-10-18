package com.sept.springboot.controller;

import com.sept.springboot.model.Booking;
import com.sept.springboot.services.BookingService;
import com.sept.springboot.services.MapValidationErrorService;
import com.sept.springboot.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.sept.springboot.model.Customer;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/*
    API that controls all functionality for the customers.
 */
@RestController
@RequestMapping("/api/customer")
@CrossOrigin
public class CustomerController
{
    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    /*
        Creates a new customer into the database.
     */
    @PostMapping("")
    public ResponseEntity<?> createNewCustomer(@Valid @RequestBody Customer customer, BindingResult result)
    {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap != null)
            return errorMap;

        Customer newCustomer = customerService.addOrUpdateCustomer(customer);

        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    /*
        Returns customer by the id (customerId) passed through.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable long id)
    {
        return new ResponseEntity<>(customerService.findByCustomerId(id), HttpStatus.OK);
    }

    /*
        Returns the customer by the email that is passed through.
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getCustomerEmail(@PathVariable String email)
    {
        return new ResponseEntity<>(customerService.findByEmail(email), HttpStatus.OK);
    }

    /*
        Returns the password of the customer with the email that is passed through.
        This is an unsecure way to allow logging in a customer in the frontend.
        Is here as a temporary loose solution.
     */
    @GetMapping("/login/{email}")
    public ResponseEntity<?> getPasswordByEmail(@PathVariable String email)
    {
        return new ResponseEntity<>(customerService.findByEmail(email).getPassword(), HttpStatus.OK);
    }

    /*
        Returns all the customers in the database.
        Returns an iterable list.
     */
    @GetMapping("/all")
    public Iterable<Customer> getAllCustomers()
    {
        return customerService.findAllCustomers();
    }

    /*
        Deletes the customer with the id (customerId) passed through.
        When a customer is deleted. All the bookings the customer made are also deleted.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable long id)
    {
        Iterable<Booking> bookings = bookingService.findByCustomerId(id);

        for(Booking t : bookings)
            bookingService.deleteByBookingId(t.getBookingId());

        customerService.deleteByCustomerId(id);

        return new ResponseEntity<>("Customer with ID: '" + id + "' was deleted", HttpStatus.OK);
    }

    /*
        This updates the customer with the id (customerId) passed through.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable(value = "id") long id, @Valid @RequestBody Customer customerDetails, BindingResult result)
    {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap != null)
            return errorMap;

        Customer customer = customerService.findByCustomerId(id);

        customer.setUsername(customerDetails.getUsername());
        customer.setPassword(customerDetails.getPassword());
        customer.setEmail(customerDetails.getEmail());

        customerService.addOrUpdateCustomer(customer);

        return new ResponseEntity<>("Customer with ID: '" + id + "' has been updated", HttpStatus.OK);
    }
}
