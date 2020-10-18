package com.sept.springboot.dao;

import com.sept.springboot.model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
    Repository for the admin model
 */
@Repository
public interface AdminRepository extends CrudRepository<Admin, Long>
{
    Admin findByAdminId(long adminId);
    Admin findByEmail(String email);

    @Override
    Iterable<Admin> findAll();
}
