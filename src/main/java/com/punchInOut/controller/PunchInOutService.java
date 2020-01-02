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
import org.springframework.stereotype.Component;

import com.punchInOut.DTO.Employee;
import com.punchInOut.DTO.EmployeeDailyPunchData;
import com.punchInOut.DTO.PunchClockData;
import com.punchInOut.DTO.PunchData;
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
		     if(shift!=0) {
			if (empDailyPunchData != null) {
				if(count.isEmpty() || count.size()<4) {
				if (empDailyPunchData.getPunchCount() >= 1 && empDailyPunchData.getPunchCount() <8 ) {
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
			employeeDailyPunchDataRepository.save(empDailyPunchData);
			}
		     else {
		    	 System.out.println("it's a holiday");
		     }
		}
	            
		}
	

	
	public PunchData getHours(PunchData punchData) {
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("E");
		 PunchData totalPunchData= new PunchData();
		 totalPunchData.setEmp(punchData.getEmp());
			List<PunchClockData> punchClockData = punchClockDataRepository.findByEmpIdAndPunchDayAndShift(punchData.getEmp(),punchData.getDate(),punchData.getShift());

		 
		 if(punchClockData.size()!=0) {
		 if(punchClockData.size()>1) {
			totalPunchData.setTotalWorkHours( Duration.between(LocalDateTime.ofInstant(punchClockData.get(0).getPunchTime().toInstant(), ZoneId.systemDefault()).toLocalTime(),LocalDateTime.ofInstant(punchClockData.get(1).getPunchTime().toInstant(), ZoneId.systemDefault()).toLocalTime()));
		}
		if(punchClockData.size()>2) {
			totalPunchData.setTotalLunchHours(Duration.between(LocalDateTime.ofInstant(punchClockData.get(1).getPunchTime().toInstant(), ZoneId.systemDefault()).toLocalTime(), LocalDateTime.ofInstant(punchClockData.get(2).getPunchTime().toInstant(), ZoneId.systemDefault()).toLocalTime()));
		}
		if(punchClockData.size()>3) {
			totalPunchData.setTotalWorkHours(totalPunchData.getTotalWorkHours().plus(Duration.between(LocalDateTime.ofInstant(punchClockData.get(2).getPunchTime().toInstant(), ZoneId.systemDefault()).toLocalTime(),LocalDateTime.ofInstant(punchClockData.get(3).getPunchTime().toInstant(), ZoneId.systemDefault()).toLocalTime())));

		}
		
		if(punchClockData.size()==3) {
			
			WorkHours workhours = workHoursRepository.findByEmpIdAndDayAndShift(punchData.getEmp(), simpleDateformat.format(punchClockData.get(0).getPunchDay()),punchData.getShift());
				String[] time = workhours.getTime().split("-");
				if(totalPunchData.getTotalLunchHours().plus(totalPunchData.getTotalWorkHours()).compareTo(Duration.between(LocalTime.parse(time[0]), LocalTime.parse(time[1])))<0) {
					Duration d= Duration.between(LocalTime.parse(time[0]), LocalTime.parse(time[1])).minus(totalPunchData.getTotalLunchHours().plus(totalPunchData.getTotalWorkHours()));
					totalPunchData.setTotalWorkHours(totalPunchData.getTotalWorkHours().plus(d));
				}
				}
	
	  	System.out.println("Total work duration In Minutes:"+totalPunchData.getTotalWorkHours().toMinutes()+"minutes In Hours:"+totalPunchData.getTotalWorkHours().toHours()+"hours");
		System.out.println("Total lunch duration In Minutes"+totalPunchData.getTotalLunchHours().toMinutes()+"minutes In Hours"+totalPunchData.getTotalLunchHours().toHours()+"hours");
		return totalPunchData;
	}
	else {return null;}
	}
	
	public PunchData getHoursOfShiftAndDay(PunchData punchData) {
		 Optional<PunchClockData> punchClockDataForFirstRecord = punchClockDataRepository.findById(1L);
		 PunchClockData punchClockDataForLastRecord  =  punchClockDataRepository.findTopByOrderByIdDesc();
		PunchData pd =  new PunchData();
		if(punchData.getShift()!=null) {
			List<EmployeeDailyPunchData> employeeDailyPunchDatas = employeeDailyPunchDataRepository.findByEmpIdAndPunchDayLessThanEqualAndPunchDayGreaterThanEqual(punchData.getEmp(), punchData.getEndDate()==null? punchClockDataForLastRecord.getPunchDay() :punchData.getEndDate(), punchData.getStartDate()==null?punchClockDataForFirstRecord.get().getPunchDay(): punchData.getStartDate());
		for(EmployeeDailyPunchData p: employeeDailyPunchDatas) {
			
			punchData.setDate(p.getPunchDay());
			PunchData temp=getHours(punchData);
			if(temp!=null) {
			pd.setTotalWorkHours(pd.getTotalWorkHours()==null?temp.getTotalWorkHours():pd.getTotalWorkHours().plus(temp.getTotalWorkHours()));
			pd.setTotalLunchHours(pd.getTotalLunchHours()==null?temp.getTotalLunchHours():pd.getTotalLunchHours().plus(temp.getTotalLunchHours()));

		}}
			return pd;
			
		}
		else {
			for (int shift = 1; shift <= 2; shift++) {
				punchData.setShift(shift);
				List<EmployeeDailyPunchData> employeeDailyPunchDatas = employeeDailyPunchDataRepository
						.findByEmpIdAndPunchDayLessThanEqualAndPunchDayGreaterThanEqual(punchData.getEmp(),
								punchData.getEndDate() == null ? punchClockDataForLastRecord.getPunchDay()
										: punchData.getEndDate(),
								punchData.getStartDate() == null ? punchClockDataForFirstRecord.get().getPunchDay()
										: punchData.getStartDate());
				for (EmployeeDailyPunchData p : employeeDailyPunchDatas) {
					punchData.setDate(p.getPunchDay());
					PunchData temp = getHours(punchData);
					if (temp != null) {
						pd.setTotalWorkHours(pd.getTotalWorkHours() == null ? temp.getTotalWorkHours()
								: pd.getTotalWorkHours().plus(temp.getTotalWorkHours()));
						pd.setTotalLunchHours(pd.getTotalLunchHours() == null ? temp.getTotalLunchHours()
								: pd.getTotalLunchHours().plus(temp.getTotalLunchHours()));
					}
				}
			}
			return pd;
		}

	}

	
	
	
	public int checkShiftOfEmployee(Employee emp) {
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("E");
		WorkHours workhoursSecondShift = workHoursRepository.findByEmpIdAndDayAndShift(emp, simpleDateformat.format(new Date()),
				2);
		if (workhoursSecondShift.getTime().isEmpty()) {
			WorkHours workhours = workHoursRepository.findByEmpIdAndDayAndShift(emp,
					simpleDateformat.format(new Date()), 1);
			if (workhours.getTime().isEmpty()) {
				return 0;
			} else {
				return 1;
			}
		}
		else {
			String[] time = workhoursSecondShift.getTime().split("-");
			if (!time[0].isEmpty()) {
				if (LocalTime.now().isAfter(LocalTime.parse(time[0]))) {
					return 2;
				} else {
					return 1;
				}
			}
			return 0;
		}
	}
	
	
}
