package com.spring.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.demo.model.LocationStats;
import com.spring.demo.services.CoronaVirusDataService;

@Controller
public class HomeController {
	
	@Autowired
	CoronaVirusDataService coronaVirusDataService;
	
	@GetMapping("/")
	public String home(Model model){
		
		List<LocationStats> allStats= coronaVirusDataService.getAllStatistics();
		int totalCases= allStats.stream().mapToInt(stat->stat.getLatestCases()).sum();
		model.addAttribute("locationStats",allStats );
		model.addAttribute("totalReportedCases",totalCases );
		return "home";
	}
	
	

}
