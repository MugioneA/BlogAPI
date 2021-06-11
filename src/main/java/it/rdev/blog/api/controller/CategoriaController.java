package it.rdev.blog.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.rdev.blog.api.service.BlogCategoriaDetailService;

@RestController
public class CategoriaController {

	@Autowired
	private BlogCategoriaDetailService blogcategoria;
	
	
	@GetMapping({ "/api/categorieall" })
	public ResponseEntity<?> getAllArticolir() {
		
		return ResponseEntity.ok(blogcategoria.find());
	}
}
