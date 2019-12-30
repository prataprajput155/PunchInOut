package com.punchInOut.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.punchInOut.DTO.Employee;
import com.punchInOut.DTO.WorkHours;

public interface WorkHoursRepository extends JpaRepository<WorkHours, Long> ,CrudRepository<WorkHours, Long> {

	
	public List<WorkHours> findByEmpIdAndDay(Employee employee,String Day);
	public WorkHours findByEmpIdAndDayAndShift(Employee employee,String Day,int shift);
	
}
