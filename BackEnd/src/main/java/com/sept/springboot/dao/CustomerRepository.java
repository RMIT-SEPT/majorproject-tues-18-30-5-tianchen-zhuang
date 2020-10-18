package com.sept.springboot.dao;

import com.sept.springboot.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
    Repository for the admin customer
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>
{
    Customer findByCustomerId(long customerId);
    Customer findByEmail(String email);

    @Override
    Iterable<Customer> findAll();
}
