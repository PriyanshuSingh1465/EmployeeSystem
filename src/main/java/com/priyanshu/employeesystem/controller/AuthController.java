package com.priyanshu.employeesystem.controller;

import com.priyanshu.employeesystem.entity.User;
import com.priyanshu.employeesystem.repository.UserRepository;
import com.priyanshu.employeesystem.request.UserRequest;
import com.priyanshu.employeesystem.response.UserResponse;
import com.priyanshu.employeesystem.service.UserService;
import com.priyanshu.employeesystem.util.JwtUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/EMS/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequest request) {

        System.out.println("AUTH CONTROLLER CALLED");

        UserResponse response = userService.createUser(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public String login(@RequestBody User request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        return jwtUtil.generateToken(request.getUsername());
    }
}
