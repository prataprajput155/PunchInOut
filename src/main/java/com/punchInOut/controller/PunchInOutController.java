package com.punchInOut.controller;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.punchInOut.DTO.EmployeeDailyPunchData;
import com.punchInOut.DTO.PunchData;
import com.punchInOut.repository.PunchInOutRepository;


@RequestMapping("/*")
@RestController
public class PunchInOutController {

	@Autowired
	PunchInOutRepository punchInOutRepository;
	
	@Autowired
	PunchInOutService punchInOutService;
	
	@GetMapping("/{id}")
	public void saveEmployeePunch(@PathVariable("id") Long Id) {
	
      punchInOutService.savePunch(Id);
		
		
	}

	@GetMapping("/get-hours")
	public PunchData gethours(PunchData punchData) {
	     if(punchData.getDate()==null) {
	    	 return punchInOutService.getTotalHoursOfAllDays(punchData);
	     }
	     else {
		return punchInOutService.getHoursOfShiftAndDay(punchData);}
		
	}
	
}
