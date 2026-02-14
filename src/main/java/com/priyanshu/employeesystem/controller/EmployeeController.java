package com.priyanshu.employeesystem.controller;

import com.priyanshu.employeesystem.request.EmployeeRequest;
import com.priyanshu.employeesystem.response.EmployeeResponse;
import com.priyanshu.employeesystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/EMS/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PreAuthorize("hasAnyRole('ADMIN','QA','USER')")
    @PostMapping("/create")
    public EmployeeResponse create(@RequestBody EmployeeRequest request) {
        return employeeService.createEmployee(request);
    }

    @PreAuthorize("hasAnyRole('ADMIN','QA','USER')")
    @GetMapping("/getAllEmployee")
    public List<EmployeeResponse> getAll() {
        return employeeService.getAllEmployees();
    }

    @PreAuthorize("hasAnyRole('ADMIN','QA','USER')")
    @GetMapping("/update/{id}")
    public EmployeeResponse getById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','QA','USER')")
    @PutMapping("/delete/{id}")
    public EmployeeResponse update(@PathVariable Long id,
                                   @RequestBody EmployeeRequest request) {
        return employeeService.updateEmployee(id, request);
    }

    @PreAuthorize("hasAnyRole('ADMIN','QA')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
