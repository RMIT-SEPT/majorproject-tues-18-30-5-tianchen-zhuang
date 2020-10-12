package com.sept.springboot.security;

import com.sept.springboot.dao.BusinessRepository;
import com.sept.springboot.dao.CustomerRepository;
import com.sept.springboot.exception.UserNotFoundException;
import com.sept.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        if(customerRepository.findByUsername(username) != null)
            return customerRepository.findByUsername(username);
        else if(businessRepository.findByUsername(username) != null)
            return businessRepository.findByUsername(username);
        else
            throw new UserNotFoundException("User not found");
    }

    @Transactional
    public User loadUserById(Long id){
        if(customerRepository.findByCustomerId(id) != null)
            return customerRepository.findByCustomerId(id);
        else if(businessRepository.findByBusinessId(id) != null)
            return businessRepository.findByBusinessId(id);
        else
            throw new UsernameNotFoundException("User not found");
    }
}
