package com.punchInOut.DTO;

import java.time.Duration;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PunchData {
     
	private Employee emp;
	
	private Integer shift;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	private String startTime;
	private String endTime;
	
	private Integer lateCount;
	private Integer earlyCount;
	private Integer ontimeCount;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	
	
	public Integer getLateCount() {
		return lateCount;
	}

	public void setLateCount(Integer lateCount) {
		this.lateCount = lateCount;
	}

	public Integer getEarlyCount() {
		return earlyCount;
	}

	public void setEarlyCount(Integer earlyCount) {
		this.earlyCount = earlyCount;
	}

	public Integer getOntimeCount() {
		return ontimeCount;
	}

	public void setOntimeCount(Integer ontimeCount) {
		this.ontimeCount = ontimeCount;
	}

	@Override
	public String toString() {
		return "PunchData [emp=" + emp + ", shift=" + shift + ", date=" + date + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", startTime=" + startTime + ", endTime=" + endTime + ", lateCount="
				+ lateCount + ", earlyCount=" + earlyCount + ", ontimeCount=" + ontimeCount + ", totalLunchHours="
				+ totalLunchHours + ", totalWorkHours=" + totalWorkHours + "]";
	}

	




	
	
	
	
}
