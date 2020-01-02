package com.punchInOut.DTO;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "employee_daily_punch_data")
public class EmployeeDailyPunchData {
   // private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Employee.class)
    @JoinColumn(name = "emp_id", referencedColumnName = "id")
    private Employee empId;
    
    @Column(name = "punch_count")
    private Integer punchCount;

    
    @Temporal(TemporalType.DATE)
    @Column(name = "punch_day")
    private Date punchDay;
    

    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public Employee getEmpId() {
		return empId;
	}
	public void setEmpId(Employee empId) {
		this.empId = empId;
	}
	public Date getPunchDay() {
		return punchDay;
	}
	public void setPunchDay(Date punchDay) {
		this.punchDay = punchDay;
	}



	public Integer getPunchCount() {
		return punchCount;
	}
	public void setPunchCount(Integer punchCount) {
		this.punchCount = punchCount;
	}
	
}
