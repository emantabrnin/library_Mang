package com.example.library.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entities.Category;
import com.example.library.repository.CategoryRepository;


@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Long getTotalCount() {
		return categoryRepository.count();
	}
	
	public List<Category> getAllBySort() {
		return categoryRepository.findAllByOrderByNameAsc();
	}
	
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}
	
	public Category get(Long id) {
		return categoryRepository.findById(id).get();
	}
	
	public Category addNew(Category category) {
		category.setCreateDate(new Date());
		return categoryRepository.save(category);
	}
	
	public Category save(Category category) {
		return categoryRepository.save(category);
	}
	
	public Category updateCategory(long id,Category category) {
		try{
           return categoryRepository.save(category);
		}catch(Exception e){}
		return null;
	}
	public Boolean delete(Long id) {
		categoryRepository.delete(categoryRepository.findById(id).orElseThrow());
		return categoryRepository.findById(id).isEmpty();
	}
	
	// public void delete(Long id) {
	// 	categoryRepository.deleteById(id);
	// }
	
	public boolean hasUsage(Category category) {
		return category.getBooks().size()>0;
	}
}
