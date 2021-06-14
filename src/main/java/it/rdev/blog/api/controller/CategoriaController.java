package it.rdev.blog.api.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.rdev.blog.api.controller.dto.CategoriaDTO;
import it.rdev.blog.api.service.BlogCategoriaDetailService;

@RestController
public class CategoriaController {

	@Autowired
	private BlogCategoriaDetailService blogcategoria;
	
	
	@GetMapping({ "/api/categorieall" })
	public ResponseEntity<?> getAllArticolir() {
		Set<CategoriaDTO> listacat = blogcategoria.find();
		if(listacat!= null && listacat.size()>0) {
		return ResponseEntity.ok(listacat);
		}
		return new  ResponseEntity<>(HttpStatus.NOT_FOUND);		
	}
}
