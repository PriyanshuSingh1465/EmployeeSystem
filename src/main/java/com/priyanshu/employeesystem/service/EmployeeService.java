package com.priyanshu.employeesystem.service;



import java.util.List;

import com.priyanshu.employeesystem.request.EmployeeRequest;
import com.priyanshu.employeesystem.response.EmployeeResponse;

public interface EmployeeService {

    EmployeeResponse createEmployee(EmployeeRequest request);
    
    List<EmployeeResponse> getAllEmployees();  

    EmployeeResponse getEmployeeById(Long id);

    EmployeeResponse updateEmployee(Long id, EmployeeRequest request);

    void deleteEmployee(Long id);
}
