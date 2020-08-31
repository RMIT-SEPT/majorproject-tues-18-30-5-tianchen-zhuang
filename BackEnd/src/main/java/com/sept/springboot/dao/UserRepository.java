package com.sept.springboot.dao;

import com.sept.springboot.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUserId(long id);
    @Override
    Iterable<User> findAll();
}
