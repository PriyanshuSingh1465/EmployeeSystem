package com.priyanshu.employeesystem.service;

import java.util.List;

import com.priyanshu.employeesystem.entity.Department;

public interface DepartmentService {

    Department createDepartment(Department department);

    List<Department> getAllDepartments();

    Department getDepartmentById(Long id);

    Department updateDepartment(Long id, Department department);

    void deleteDepartment(Long id);
}

