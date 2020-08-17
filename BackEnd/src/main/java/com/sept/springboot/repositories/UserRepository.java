package com.sept.springboot.repositories;

import com.sept.springboot.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    @Override
    Iterable<User> findAllById(Iterable<Long> iterable);
}
