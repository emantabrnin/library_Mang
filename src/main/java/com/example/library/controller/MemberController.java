package com.example.library.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.library.Constants;
import com.example.library.entities.Member;
import com.example.library.service.MemberService;



@Controller
@RequestMapping(value = "/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@ModelAttribute(name = "memberTypes")
	public List<String> memberTypes() {
		return Constants.MEMBER_TYPES;
	}
	
	@GetMapping(path = "/list")
	public List<Member> showMembersPage() {
		return memberService.getAll();
		
	}
	
	@PostMapping(path = "/add")
	public Object addMemeberPage(@RequestBody Member member) {
		try{
            return memberService.addNew(member);
		}catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
	}
	
	@PutMapping(value = "/edit/{id}")
	public Object editMemeberPage(@PathVariable(name = "id") Long id,@RequestBody Member member) {
		try{
         Member member2 = new Member();
		 member2.setEmail(member.getEmail());
		 member2.setFirstName(member.getFirstName());
		 member2.setContact(member.getContact());
		 member2.setGender(member.getGender());
		 member2.setLastName(member.getLastName());
		 member2.setType(member.getType());
		 member2.setDateOfBirth(member.getDateOfBirth());
		 member2.setMiddleName(member.getMiddleName());
		 member2.setJoiningDate(member.getJoiningDate());
		 return member2;

		}catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
	}
	
	@PostMapping(value = "/save")
	public Object saveMember(@Valid @RequestBody Member member) {
	   return memberService.addNew(member);
	}
	
	@GetMapping(path  = "/remove/{id}")
	public Boolean removeMember(@PathVariable(name = "id") Long id) {
	     return memberService.delete1(id);
	}
	
	
	
	
}
