package com.sept.springboot.services;

import com.sept.springboot.model.Business;
import com.sept.springboot.dao.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    public Business saveOrUpdateBusiness(Business business)
    {
        // Business logic
        return businessRepository.save(business);
    }
}
