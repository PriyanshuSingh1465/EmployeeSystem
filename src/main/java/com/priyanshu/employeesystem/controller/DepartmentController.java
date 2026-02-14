package com.priyanshu.employeesystem.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priyanshu.employeesystem.entity.Department;
import com.priyanshu.employeesystem.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/EMS/departments")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PreAuthorize("hasAnyRole('ADMIN','QA')")
    @PostMapping("/create")
    public Department create(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }
    
    @PreAuthorize("hasAnyRole('ADMIN','QA','USER')")
    @GetMapping("/getAllDepartments")
    public List<Department> getAll() {
    	
        return departmentService.getAllDepartments();
    }
    
    @PreAuthorize("hasAnyRole('ADMIN','QA')")
    @PutMapping("/update/{id}")
  
    public Department update(@PathVariable Long id,
                             @RequestBody Department department) {
        return departmentService.updateDepartment(id, department);
    }
    
    @PreAuthorize("hasAnyRole('ADMIN','QA')")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }
}

