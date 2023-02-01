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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.library.entities.Book;
import com.example.library.entities.Category;
import com.example.library.service.BookService;
import com.example.library.service.CategoryService;



@Controller
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(path  = "/categories")
	public List<Category> categories() {
		return categoryService.getAllBySort();
	}
	
	@GetMapping(path =  "/list")
	public List<Book> showBooksPage(Model model) {
		
		return bookService.getAll();
	}
	
	@PostMapping(path = "/add")
	public Object addBookPage(@RequestBody Book book) {
	
		return bookService.addNew(book);
	}
	
	@PutMapping(path  = "/edit/{id}")
	public Object editBookPage(@PathVariable(name = "id") Long id, @RequestBody Book book  ) {
		try{
           return bookService.updateBook(book, id);
		}catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
		
	}
	
	@GetMapping(path = "/geAvailabletByCategory")
	public Object geAvailabletByCategory(@RequestBody Category category) {
		return bookService.geAvailabletByCategory(category);
	}

	@GetMapping(path = "/getByCategory")
	public Object getByCategory(@RequestBody Category category) {
		return bookService.getByCategory(category);
	}
	
	@DeleteMapping(path = "/remove/{id}")
	public Object removeBook(@PathVariable(name = "id") Long id, Model model) {
	   return bookService.delete(id);
	}
}
