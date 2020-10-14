package com.sept.springboot.services;

import com.sept.springboot.exception.UserNotFoundException;
import com.sept.springboot.model.Customer;
import com.sept.springboot.dao.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService
{
    @Autowired
    private CustomerRepository customerRepository;

    public Customer addOrUpdateCustomer(Customer customer)
    {
        try
        {
            return customerRepository.save(customer);
        }
        catch (Exception e)
        {
            throw new UserNotFoundException("Customer ID '" + customer.getCustomerId() + "' already exists");
        }
    }

    public Customer findByCustomerId(long id)
    {
        Customer customer = customerRepository.findByCustomerId(id);

        if(customer == null)
            throw new UserNotFoundException("Customer ID '" + id + "' does not exist");

        return customer;
    }

    public Customer findByEmail(String email)
    {
        Customer customer = customerRepository.findByEmail(email);

        if(customer == null)
            throw new UserNotFoundException("User with email '" + email + "' does not exist");

        return customer;
    }

    public Iterable<Customer> findAllCustomers()
    {
        return customerRepository.findAll();
    }

    public void deleteByCustomerId(long id)
    {
        Customer customer = customerRepository.findByCustomerId(id);

        if(customer == null)
            throw new UserNotFoundException("Cannot delete customer with ID '" + id + "'. This customer does not exist");

        customerRepository.delete(customer);
    }
}
