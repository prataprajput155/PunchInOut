package com.punchInOut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.punchInOut.DTO.Employee;

public interface PunchInOutRepository extends JpaRepository<Employee, Long> ,CrudRepository<Employee, Long> {

	
	
	
}
