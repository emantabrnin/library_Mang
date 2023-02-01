package com.example.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.service.HomeService;



@RestController
public class HomeController {

	@Autowired
	HomeService homeService;
	
	@GetMapping(path ="/home")
	public Object homePage() {
		
		return  homeService.getTopTilesMap();
	}	
	
}
