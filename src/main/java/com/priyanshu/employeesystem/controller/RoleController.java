package com.priyanshu.employeesystem.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priyanshu.employeesystem.entity.Role;
import com.priyanshu.employeesystem.service.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/EMS/roles")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {

    private final RoleService roleService;

    @PreAuthorize("hasAnyRole('ADMIN','QA')")
    @PostMapping("/create")
    public Role create(@RequestBody Role role) {
        return roleService.createRole(role);
    }
    
    @PreAuthorize("hasAnyRole('ADMIN','QA')")
    @GetMapping("getAllRoles")
    public List<Role> getAll() {
        return roleService.getAllRoles();
    }
    
    @PreAuthorize("hasAnyRole('ADMIN','QA')")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        roleService.deleteRole(id);
    }
}

