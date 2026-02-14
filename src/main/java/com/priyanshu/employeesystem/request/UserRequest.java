package com.priyanshu.employeesystem.request;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserRequest {

    private String username;
    private String password;
    private String email;
    private String phone;

    private String city;
    private String state;
    private String country;
    private String zipCode;

    private Long departmentId;
    private Set<String> roleNames;
}
