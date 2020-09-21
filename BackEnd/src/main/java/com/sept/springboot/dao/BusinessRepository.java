package com.sept.springboot.dao;

import com.sept.springboot.model.Business;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends CrudRepository<Business, Long>
{
    Business findByBusinessId(long businessId);
    Business findByEmail(String email);

    @Override
    Iterable<Business> findAll();
}
