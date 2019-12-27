package com.punchInOut.repository;


import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.punchInOut.DTO.Employee;
import com.punchInOut.DTO.EmployeeDailyPunchData;

public interface EmployeeDailyPunchDataRepository extends JpaRepository<EmployeeDailyPunchData, Long> ,CrudRepository<EmployeeDailyPunchData, Long> {

	public EmployeeDailyPunchData findByEmpIdAndPunchDay(Employee Id,Date date);
	
	
}
