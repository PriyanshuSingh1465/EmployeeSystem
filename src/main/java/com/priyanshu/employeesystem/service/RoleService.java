package com.priyanshu.employeesystem.service;

import java.util.List;

import com.priyanshu.employeesystem.entity.Role;

public interface RoleService {

    Role createRole(Role role);

    List<Role> getAllRoles();

    void deleteRole(Long id);


}
