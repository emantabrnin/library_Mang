package com.example.library.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.library.entities.Category;
import com.example.library.service.CategoryService;


@Controller
@RequestMapping(value = "/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(path = {"/", "/list"})
	public List<Category> showCategoriesPage(Model model) {
		
		return categoryService.getAll();
	}
	
	@PostMapping(value = "/add")
	public Object addCategoryPage(@RequestBody Category category) {
		
		return categoryService.addNew(category);
	}
	
	@PutMapping(path = "/edit/{id}")
	public Object editCategoryPage(@PathVariable(name = "id") Long id, Category category) {
		try{
			Category category2 = new Category();
			category2.setName(category.getName());
			category2.setNotes(category.getNotes());
			category2.setShortName(category.getShortName());
			category2.setCreateDate(category.getCreateDate());
			return category2;
		} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
	}
	
	
	
@DeleteMapping(path ="/delete" )
  public Boolean delete(@RequestParam Long id){
	return categoryService.delete(id);
  }	
}
