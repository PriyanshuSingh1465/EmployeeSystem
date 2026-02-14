package com.priyanshu.employeesystem.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class EmployeeResponse {

    private Long id;
    private String employeeCode;
    private String fullName;
    private String email;
    private String phone;
    private String designation;
    private Double salary;
    private LocalDate joiningDate;
    private String departmentName;
}
