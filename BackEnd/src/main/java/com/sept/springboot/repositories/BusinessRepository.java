package com.sept.springboot.repositories;

import com.sept.springboot.model.Business;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends CrudRepository<Business, Long>
{
    Business findByBusinessId(long id);
    @Override
    Iterable<Business> findAll();
}
