package com.punchInOut.controller;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.punchInOut.DTO.PunchData;
import com.punchInOut.repository.PunchInOutRepository;


@RequestMapping(value="/*", method = {RequestMethod.GET,RequestMethod.POST})
@RestController
public class PunchInOutController {

	@Autowired
	PunchInOutRepository punchInOutRepository;
	
	@Autowired
	PunchInOutService punchInOutService;
	
	@PostMapping("/{id}")
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
