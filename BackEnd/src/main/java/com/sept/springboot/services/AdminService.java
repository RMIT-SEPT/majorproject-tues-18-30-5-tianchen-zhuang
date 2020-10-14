package com.sept.springboot.services;

import com.sept.springboot.dao.AdminRepository;
import com.sept.springboot.exception.UserNotFoundException;
import com.sept.springboot.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService
{
    @Autowired
    private AdminRepository adminRepository;

    public Admin addOrUpdateAdmin(Admin admin)
    {
        try
        {
            return adminRepository.save(admin);
        }
        catch (Exception e)
        {
            throw new UserNotFoundException("Admin ID '" + admin.getAdminId() + "' already exists");
        }
    }

    public Admin findByAdminId(long id)
    {
        Admin admin = adminRepository.findByAdminId(id);

        if(admin == null)
            throw new UserNotFoundException("Admin ID '" + id + "' does not exist");

        return admin;
    }

    public Admin findByEmail(String email)
    {
        Admin admin = adminRepository.findByEmail(email);

        if(admin == null)
            throw new UserNotFoundException("Admin with email '" + email + "' does not exist");

        return admin;
    }

    public Iterable<Admin> findAllAdmins()
    {
        return adminRepository.findAll();
    }

    public void deleteByAdminId(long id)
    {
        Admin admin = adminRepository.findByAdminId(id);

        if(admin == null)
            throw new UserNotFoundException("Cannot delete admin with ID '" + id + "'. This admin does not exist");

        adminRepository.delete(admin);
    }
}
