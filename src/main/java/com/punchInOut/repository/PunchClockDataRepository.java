package com.punchInOut.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.punchInOut.DTO.Employee;
import com.punchInOut.DTO.PunchClockData;

public interface PunchClockDataRepository extends JpaRepository<PunchClockData, Long> ,CrudRepository<PunchClockData, Long> {

	public List<PunchClockData> findByEmpIdAndPunchDay(Employee Id,Date punchDay);
	public List<PunchClockData> findByEmpId(Long Id);
	
	
}
