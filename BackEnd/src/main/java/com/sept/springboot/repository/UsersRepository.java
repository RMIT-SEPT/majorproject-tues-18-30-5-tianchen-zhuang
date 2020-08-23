package com.sept.springboot.repository;

import com.sept.springboot.model.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}