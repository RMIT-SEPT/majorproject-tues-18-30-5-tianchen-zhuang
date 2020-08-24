package com.sept.springboot.repositories;

import com.sept.springboot.model.Business;
import org.springframework.data.repository.CrudRepository;

public interface BusinessRepository extends CrudRepository<Business, Long>
{
    @Override
    Iterable<Business> findAllById(Iterable<Long> iterable);
}
