package com.punchInOut.DTO;

import java.time.Duration;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PunchData {
     
	private Employee emp;
	
	private Integer shift;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	private Duration totalLunchHours;
	
	private Duration totalWorkHours;
	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}



	public Integer getShift() {
		return shift;
	}

	public void setShift(Integer shift) {
		this.shift = shift;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Duration getTotalLunchHours() {
		return totalLunchHours;
	}

	public void setTotalLunchHours(Duration totalLunchHours) {
		this.totalLunchHours = totalLunchHours;
	}

	public Duration getTotalWorkHours() {
		return totalWorkHours;
	}

	public void setTotalWorkHours(Duration totalWorkHours) {
		this.totalWorkHours = totalWorkHours;
	}

	@Override
	public String toString() {
		return "PunchData [emp=" + emp + ", shift=" + shift + ", date=" + date + ", totalLunchHours=" + totalLunchHours
				+ ", totalWorkHours=" + totalWorkHours + "]";
	}


	
	
	
	
}
