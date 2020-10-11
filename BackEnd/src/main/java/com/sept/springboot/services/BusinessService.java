package com.sept.springboot.services;

import com.sept.springboot.exception.UserNotFoundException;
import com.sept.springboot.model.Business;
import com.sept.springboot.dao.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    public Business addOrUpdateBusiness(Business business)
    {
        try
        {
            return businessRepository.save(business);
        }
        catch (Exception e)
        {
            throw new UserNotFoundException("Business ID '" + business.getBusinessId() + "' already exists");
        }
    }

    public Business findByBusinessId(long id)
    {
        Business business = businessRepository.findByBusinessId(id);

        if(business == null)
            throw new UserNotFoundException("Business ID '" + id + "' does not exist");

        return business;
    }

    public Business findByEmail(String email)
    {
        Business business = businessRepository.findByEmail(email);

        if(business == null)
            throw new UserNotFoundException("User with email '" + email + "' does not exist");

        return business;
    }

    public Iterable<Business> findAllBusinesses()
    {
        return businessRepository.findAll();
    }

    public void deleteByBusinessId(long id)
    {
        Business business = businessRepository.findByBusinessId(id);

        if(business == null)
            throw new UserNotFoundException("Cannot delete business with ID '" + id + "'. This business does not exist");

        businessRepository.delete(business);
    }
}
