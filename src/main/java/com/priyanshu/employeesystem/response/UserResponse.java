package com.priyanshu.employeesystem.response;


import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String phone;
    private String status;

    private String city;
    private String state;
    private String country;
    private String zipCode;

    private String departmentName;
    private Set<String> roles;
}
