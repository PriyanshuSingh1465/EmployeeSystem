package com.priyanshu.employeesystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.priyanshu.employeesystem.entity.Role;
import com.priyanshu.employeesystem.entity.User;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

}

