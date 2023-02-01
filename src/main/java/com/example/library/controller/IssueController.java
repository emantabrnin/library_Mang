package com.example.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.library.Constants;
import com.example.library.entities.Category;
import com.example.library.entities.Issue;
import com.example.library.service.CategoryService;
import com.example.library.service.IssueService;



@Controller
@RequestMapping(value = "/issue")
public class IssueController {

	@Autowired
	private IssueService issueService;
	
	@Autowired
	private CategoryService categoryService;
	
	@ModelAttribute(name = "memberTypes")
	public List<String> memberTypes() {
		return Constants.MEMBER_TYPES;
	}
	
	@GetMapping(path="/categories")
	public List<Category> getCategories() {
		return categoryService.getAllBySort();
	}
	
	@GetMapping(path =  "/list")
	public List<Issue> listIssuePage() {
		
		return  issueService.getAllUnreturned();
	}
	
	@PostMapping(path = "/new")
	public Object newIssuePage(Issue issue) { 
		return issueService.addNew(issue);
	}
	
}
