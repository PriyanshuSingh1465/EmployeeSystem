package com.priyanshu.employeesystem.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.priyanshu.employeesystem.entity.Role;
import com.priyanshu.employeesystem.repository.RoleRepository;
import com.priyanshu.employeesystem.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}

