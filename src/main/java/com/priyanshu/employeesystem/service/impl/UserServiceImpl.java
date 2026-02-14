package com.priyanshu.employeesystem.service.impl;


import com.priyanshu.employeesystem.entity.*;
import com.priyanshu.employeesystem.repository.*;
import com.priyanshu.employeesystem.request.UserRequest;
import com.priyanshu.employeesystem.response.UserResponse;
import com.priyanshu.employeesystem.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(UserRequest request) {

        if (request.getRoleNames() == null || request.getRoleNames().isEmpty()) {
            throw new RuntimeException("At least one role must be provided");
        }

        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setCity(request.getCity());
        user.setState(request.getState());
        user.setCountry(request.getCountry());
        user.setZipCode(request.getZipCode());
        user.setStatus("ACTIVE");

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        user.setDepartment(department);

        Set<Role> roles = request.getRoleNames().stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                .collect(Collectors.toSet());

        user.setRoles(roles);

        User savedUser = userRepository.save(user);
        
        System.out.println("Roles found: " + roles);


        return mapToResponse(savedUser);
    }

    @Override
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(this::mapToResponse);
    }


    @Override
    public UserResponse getUserById(Long id) {
        return mapToResponse(
                userRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("User not found"))
        );
    }

    @Override
    public UserResponse assignRole(Long userId, String roleName) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        if (user.getRoles() == null) {
            user.setRoles(Set.of(role));
        } else {
            user.getRoles().add(role);
        }

        return mapToResponse(userRepository.save(user));
    }

    @Override
    public UserResponse transferDepartment(Long userId, Long departmentId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        user.setDepartment(department);

        return mapToResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .status(user.getStatus())
                .city(user.getCity())
                .state(user.getState())
                .country(user.getCountry())
                .zipCode(user.getZipCode())
                .departmentName(user.getDepartment() != null ? user.getDepartment().getName() : null)
                .roles(user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet()))
                .build();
    }

	@Override
	public List<UserResponse> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
}
