package com.sept.springboot.security;

import com.sept.springboot.dao.BusinessRepository;
import com.sept.springboot.dao.CustomerRepository;
import com.sept.springboot.exception.UserNotFoundException;
import com.sept.springboot.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;

public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(customerRepository.findByUsername(username) != null)
        {
            return customerRepository.findByUsername(username);
        }
        else if(businessRepository.findByUsername(username) != null)
        {
            return businessRepository.findByUsername(username);
        }
        else {
            new UserNotFoundException("User not found");
        }

        return null;
    }


}
