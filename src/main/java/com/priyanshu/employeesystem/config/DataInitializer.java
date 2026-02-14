package com.priyanshu.employeesystem.config;

import com.priyanshu.employeesystem.entity.*;
import com.priyanshu.employeesystem.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {

        // Create roles if not exist
        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
        	Role adminRole = new Role();
        	adminRole.setName("ROLE_ADMIN");
        	roleRepository.save(adminRole);
        }

        if (roleRepository.findByName("ROLE_USER").isEmpty()) {
        	Role userRole = new Role();
        	userRole.setName("ROLE_USER");
        	roleRepository.save(userRole);


        }

        // Create departments
        if (departmentRepository.count() == 0) {
        	Department it = new Department();
        	it.setName("IT");
        	it.setDescription("Technology Team");
        	departmentRepository.save(it);
       
        	it.setName("HR");
        	it.setDescription("Human Resource Team");
        	departmentRepository.save(it);
        	
        	it.setName("FINANCE");
        	it.setDescription("Finance Team");
        	departmentRepository.save(it);
        }

        // Create default admin
        if (userRepository.findByUsername("admin").isEmpty()) {

            Role adminRole = roleRepository.findByName("ROLE_ADMIN").get();
            Department itDept = departmentRepository.findAll().get(0);

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@company.com");
            admin.setPhone("9999999999");
            admin.setStatus("ACTIVE");
            admin.setCity("Gurgaon");
            admin.setState("Haryana");
            admin.setCountry("India");
            admin.setDepartment(itDept);
            admin.getRoles().add(adminRole);

            userRepository.save(admin);
        }
    }
}
