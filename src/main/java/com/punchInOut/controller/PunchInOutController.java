package com.punchInOut.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.punchInOut.repository.PunchInOutRepository;


@RequestMapping("/*")
@RestController
public class PunchInOutController {

	@Autowired
	PunchInOutRepository punchInOutRepository;
	
	@Autowired
	PunchInOutService punchInOutService;
	
	@GetMapping("/{id}")
	public void getEmployee(@PathVariable("id") Long Id) {
	
      punchInOutService.savePunch(Id);
		
		
	}
	
   
	
}
