package com.punchInOut.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.punchInOut.DTO.Employee;
import com.punchInOut.DTO.EmployeeDailyPunchData;
import com.punchInOut.DTO.PunchClockData;
import com.punchInOut.repository.EmployeeDailyPunchDataRepository;
import com.punchInOut.repository.PunchClockDataRepository;
import com.punchInOut.repository.PunchInOutRepository;


@Component
public class PunchInOutService {

	@Autowired
	PunchInOutRepository punchInOutRepository;
	
    @Autowired
    PunchClockDataRepository punchClockDataRepository;
    
    @Autowired
    EmployeeDailyPunchDataRepository employeeDailyPunchDataRepository;
	
	
	public void savePunch(Long Id) {
		Optional<Employee> emp= punchInOutRepository.findById(Id);
		if(emp.get()!=null) {
			EmployeeDailyPunchData empDailyPunchData=null;
			Employee employee = new Employee();
			employee.setId(Id);
			PunchClockData punchClockDataNew = new PunchClockData();
		     empDailyPunchData=employeeDailyPunchDataRepository.findByEmpIdAndPunchDay(employee,new Date());
		     
		//if(LocalTime.now().isBefore(LocalTime.of(14, 00, 00, 11001)) && LocalTime.now().isAfter(LocalTime.of(07, 00, 00, 11001))) {
			if (empDailyPunchData != null) {
				if (empDailyPunchData.getPunchCount() >= 1 && empDailyPunchData.getPunchCount() <4) {
					addHours(employee, empDailyPunchData);
					punchClockDataNew.setEmpId(emp.get());
		            punchClockDataNew.setPunchDay(new Date());
		            punchClockDataNew.setPunchTime(new Date());
		            punchClockDataRepository.save(punchClockDataNew);
					empDailyPunchData.setPunchCount(empDailyPunchData.getPunchCount() + 1);
				}
			

			} else {
				empDailyPunchData = new EmployeeDailyPunchData();
				empDailyPunchData.setEmpId(emp.get());
				empDailyPunchData.setPunchCount(1);
				empDailyPunchData.setPunchDay(new Date());
				
				punchClockDataNew.setEmpId(emp.get());
	            punchClockDataNew.setPunchDay(new Date());
	            punchClockDataNew.setPunchTime(new Date());
	            punchClockDataRepository.save(punchClockDataNew);
			}
		
			employeeDailyPunchDataRepository.save(empDailyPunchData);
		}
				
	            
		}

//	}
	
	public void addHours(Employee emp,EmployeeDailyPunchData employeeDailyPunchData) {

		List<PunchClockData> punchClockData = punchClockDataRepository.findByEmpIdAndPunchDay(emp,new Date());
		
		
		if(employeeDailyPunchData.getPunchCount()==1 ) {
			
			employeeDailyPunchData.setTotalWorkHours( Duration.between(LocalDateTime.ofInstant(punchClockData.get(0).getPunchTime().toInstant(), ZoneId.systemDefault()).toLocalTime(), LocalTime.now()));
		}
		else if(employeeDailyPunchData.getPunchCount()==2 ) {
			employeeDailyPunchData.setTotalLunchHours( Duration.between(LocalDateTime.ofInstant(punchClockData.get(1).getPunchTime().toInstant(), ZoneId.systemDefault()).toLocalTime(), LocalTime.now()));

		}
		else if(employeeDailyPunchData.getPunchCount()==3 ) {

			employeeDailyPunchData.setTotalWorkHours(employeeDailyPunchData.getTotalWorkHours().plus(Duration.between(LocalDateTime.ofInstant(punchClockData.get(2).getPunchTime().toInstant(), ZoneId.systemDefault()).toLocalTime(), LocalTime.now())));

		}
		employeeDailyPunchDataRepository.save(employeeDailyPunchData);
		
	}
	
	
	
	public void test() {
		List<PunchClockData> clockDatas = punchClockDataRepository.findAll();
		List<EmployeeDailyPunchData> em = employeeDailyPunchDataRepository.findAll();
		for (PunchClockData p : clockDatas) {
		 
		 LocalTime time = LocalDateTime.ofInstant(p.getPunchTime().toInstant(), ZoneId.systemDefault()).toLocalTime();
		 System.out.println(time+"here the time is");
		 
	 }
		for(EmployeeDailyPunchData e:em) {
			System.out.println(e.getTotalWorkHours().toMinutes()+"minutes"+e.getTotalLunchHours().toMinutes());
		}
	}

}
