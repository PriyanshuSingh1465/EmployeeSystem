package com.priyanshu.employeesystem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.priyanshu.employeesystem.request.UserRequest;
import com.priyanshu.employeesystem.response.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse assignRole(Long userId, String roleName);

    UserResponse transferDepartment(Long userId, Long departmentId);

    void deleteUser(Long id);

	Page<UserResponse> getAllUsers(Pageable pageable);
}
