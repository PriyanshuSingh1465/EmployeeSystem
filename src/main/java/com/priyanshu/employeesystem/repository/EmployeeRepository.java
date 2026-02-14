package com.priyanshu.employeesystem.repository;

import com.priyanshu.employeesystem.entity.Employee;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	List<Employee> findByDeletedFalse();
	
	@Query("SELECT e FROM Employee e WHERE e.deleted = false")
	Page<Employee> findActiveEmployees(Pageable pageable);
	
	Page<Employee> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);



}
