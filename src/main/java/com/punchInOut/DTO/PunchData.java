package com.punchInOut.DTO;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;



public class PunchData {
     
	private Employee emp;
	
	private int shift;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public int getShift() {
		return shift;
	}

	public void setShift(int shift) {
		this.shift = shift;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "punchData [emp=" + emp + ", shift=" + shift + ", date=" + date + "]";
	}
	
	
	
	
}
