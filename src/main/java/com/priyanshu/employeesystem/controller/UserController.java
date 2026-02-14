package com.priyanshu.employeesystem.controller;


import com.priyanshu.employeesystem.request.UserRequest;
import com.priyanshu.employeesystem.response.UserResponse;
import com.priyanshu.employeesystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/EMS/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAnyRole('ADMIN','QA')")
    @PostMapping("/create")
    public UserResponse createUser(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }

   
    @PreAuthorize("hasAnyRole('ADMIN','QA')")
    @GetMapping("/getAllUsers")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasAnyRole('ADMIN','QA','USER')")
    @GetMapping("getUser/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/assignRoleToUser")
    @PreAuthorize("hasRole('ADMIN','QA')")
    public UserResponse assignRole(@PathVariable Long userId,
                                   @PathVariable String roleName) {
        return userService.assignRole(userId, roleName);
    }

    @PutMapping("/assignDepartmentToUser")
    @PreAuthorize("hasRole('ADMIN','QA')")
    public UserResponse transferDepartment(@PathVariable Long userId,
                                           @PathVariable Long departmentId) {
        return userService.transferDepartment(userId, departmentId);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN','QA')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
