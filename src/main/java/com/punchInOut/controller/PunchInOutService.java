package com.punchInOut.controller;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.punchInOut.DTO.Employee;
import com.punchInOut.DTO.EmployeeDailyPunchData;
import com.punchInOut.DTO.PunchClockData;
import com.punchInOut.DTO.WorkHours;
import com.punchInOut.repository.EmployeeDailyPunchDataRepository;
import com.punchInOut.repository.PunchClockDataRepository;
import com.punchInOut.repository.PunchInOutRepository;
import com.punchInOut.repository.WorkHoursRepository;


@Component
public class PunchInOutService {

	@Autowired
	PunchInOutRepository punchInOutRepository;
	
    @Autowired
    PunchClockDataRepository punchClockDataRepository;
    
    @Autowired
    EmployeeDailyPunchDataRepository employeeDailyPunchDataRepository;
    
    @Autowired
    WorkHoursRepository workHoursRepository;
	
	
	public void savePunch(Long Id) {
		Optional<Employee> emp= punchInOutRepository.findById(Id);
		if(emp.get()!=null) {
			Date now = new Date();
			EmployeeDailyPunchData empDailyPunchData=null;
			Employee employee = new Employee();
			employee.setId(Id);
			 int shift=  checkShiftOfEmployee(employee);
			PunchClockData punchClockDataNew = new PunchClockData();
		     empDailyPunchData=employeeDailyPunchDataRepository.findByEmpIdAndPunchDay(employee,now);
		    List<PunchClockData> count= punchClockDataRepository.findByEmpIdAndPunchDayAndShift(employee, now, shift);
		     
			if (empDailyPunchData != null) {
				if(count.isEmpty() || count.size()<4) {
				if (empDailyPunchData.getPunchCount() >= 1 && empDailyPunchData.getPunchCount() <8 ) {
					addHours(employee, empDailyPunchData,shift);
					punchClockDataNew.setEmpId(emp.get());
		            punchClockDataNew.setPunchDay(now);
		            punchClockDataNew.setPunchTime(now);
		            punchClockDataNew.setShift(shift);
		            punchClockDataRepository.save(punchClockDataNew);
					empDailyPunchData.setPunchCount(empDailyPunchData.getPunchCount() + 1);
					}

				}
			} else {
				empDailyPunchData = new EmployeeDailyPunchData();
				empDailyPunchData.setEmpId(emp.get());
				empDailyPunchData.setPunchCount(1);
				empDailyPunchData.setPunchDay(now);
	            punchClockDataNew.setShift(shift);

				punchClockDataNew.setEmpId(emp.get());
	            punchClockDataNew.setPunchDay(now);
	            punchClockDataNew.setPunchTime(now);
	            punchClockDataRepository.save(punchClockDataNew);
			
			}
			employeeDailyPunchDataRepository.save(empDailyPunchData);}
	            
		}
	
	public void addHours(Employee emp,EmployeeDailyPunchData employeeDailyPunchData,int shift) {

		List<PunchClockData> punchClockData = punchClockDataRepository.findByEmpIdAndPunchDayAndShift(emp,new Date(),shift);
		
		if(punchClockData.size()==1) {
			
			employeeDailyPunchData.setTotalWorkHours(shift==2 && employeeDailyPunchData.getTotalWorkHours()!=null?employeeDailyPunchData.getTotalWorkHours().plus(Duration.between(LocalDateTime.ofInstant(punchClockData.get(0).getPunchTime().toInstant(), ZoneId.systemDefault()).toLocalTime(), LocalTime.now())): Duration.between(LocalDateTime.ofInstant(punchClockData.get(0).getPunchTime().toInstant(), ZoneId.systemDefault()).toLocalTime(), LocalTime.now()));
		}
		else if(punchClockData.size()==2) {
			employeeDailyPunchData.setTotalLunchHours(shift==2 && employeeDailyPunchData.getTotalLunchHours()!=null?employeeDailyPunchData.getTotalLunchHours().plus(Duration.between(LocalDateTime.ofInstant(punchClockData.get(1).getPunchTime().toInstant(), ZoneId.systemDefault()).toLocalTime(), LocalTime.now())):Duration.between(LocalDateTime.ofInstant(punchClockData.get(1).getPunchTime().toInstant(), ZoneId.systemDefault()).toLocalTime(), LocalTime.now()));

		}
		else if(punchClockData.size()==3) {

			employeeDailyPunchData.setTotalWorkHours(employeeDailyPunchData.getTotalWorkHours().plus(Duration.between(LocalDateTime.ofInstant(punchClockData.get(2).getPunchTime().toInstant(), ZoneId.systemDefault()).toLocalTime(), LocalTime.now())));

		}
		employeeDailyPunchDataRepository.save(employeeDailyPunchData);
		
	}
	
	public EmployeeDailyPunchData getHours(Employee emp,Date date,int shift) {
		EmployeeDailyPunchData employeeDailyPunchData=employeeDailyPunchDataRepository.findByEmpIdAndPunchDay(emp,date);
		List<PunchClockData> punchClockData = punchClockDataRepository.findByEmpIdAndPunchDayAndShift(emp,date,shift);
		
		if(punchClockData.size()==1) {
			
			employeeDailyPunchData.setTotalWorkHours( Duration.between(LocalDateTime.ofInstant(punchClockData.get(0).getPunchTime().toInstant(), ZoneId.systemDefault()).toLocalTime(), LocalTime.now()));
		}
		else if(punchClockData.size()==2) {
			employeeDailyPunchData.setTotalLunchHours(Duration.between(LocalDateTime.ofInstant(punchClockData.get(1).getPunchTime().toInstant(), ZoneId.systemDefault()).toLocalTime(), LocalTime.now()));
		}
		else if(punchClockData.size()==3) {

			employeeDailyPunchData.setTotalWorkHours(employeeDailyPunchData.getTotalWorkHours().plus(Duration.between(LocalDateTime.ofInstant(punchClockData.get(2).getPunchTime().toInstant(), ZoneId.systemDefault()).toLocalTime(), LocalTime.now())));

		}
		
		return employeeDailyPunchData;
	}
	
	
	public void test() {
		List<PunchClockData> clockDatas = punchClockDataRepository.findAll();
		List<EmployeeDailyPunchData> em = employeeDailyPunchDataRepository.findAll();
		for (PunchClockData p : clockDatas) {

			LocalTime time = LocalDateTime.ofInstant(p.getPunchTime().toInstant(), ZoneId.systemDefault())
					.toLocalTime();
			System.out.println(time + "here the time is");

		}
		for (EmployeeDailyPunchData e : em) {
			System.out.println(e.getTotalWorkHours().toMinutes() + "minutes" + e.getTotalLunchHours().toMinutes());
		}
	}
	
	
	
	public int checkShiftOfEmployee(Employee emp){
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("E");
		WorkHours workhours = workHoursRepository.findByEmpIdAndDayAndShift(emp, simpleDateformat.format(new Date()),2);
		
			String[] time = workhours.getTime().split("-");

			if (LocalTime.now().isAfter(LocalTime.parse(time[0]))) {
				return 2;
			} else {
				return 1;
			}
	}
	
	

	@Scheduled(cron = "30 23 * * * *")
	public void addForgotEndDayPunch() {SimpleDateFormat simpleDateformat = new SimpleDateFormat("E");
		List<Employee> emp = punchInOutRepository.findAll();
		for(int i=0; i<=emp.size();i++) {
		for(int shift=1;shift<=2;shift++) {
		List<PunchClockData> clockDatas = punchClockDataRepository.findByEmpIdAndPunchDayAndShift(emp.get(i),new Date(), shift);
		if(clockDatas.size()==3) {
			EmployeeDailyPunchData empDPD = employeeDailyPunchDataRepository.findByEmpIdAndPunchDay(emp.get(i), new Date());
			
			WorkHours workhours = workHoursRepository.findByEmpIdAndDayAndShift(emp.get(i), simpleDateformat.format(new Date()),shift);
				String[] time = workhours.getTime().split("-");
				if(empDPD.getTotalLunchHours().plus(empDPD.getTotalWorkHours()).compareTo(Duration.between(LocalTime.parse(time[0]), LocalTime.parse(time[1])))<0) {
					Duration d= Duration.between(LocalTime.parse(time[0]), LocalTime.parse(time[1])).minus(empDPD.getTotalLunchHours().plus(empDPD.getTotalWorkHours()));
					empDPD.setTotalWorkHours(empDPD.getTotalWorkHours().plus(d));
					employeeDailyPunchDataRepository.save(empDPD);
				}
				
					// clockDatas.get(3).getPunchTime()

				}

			}
		}

	}
	
	
	
	
	
}
