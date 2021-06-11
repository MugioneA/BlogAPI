package it.rdev.blog.api.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.rdev.blog.api.controller.dto.ArticoloDTO;
import it.rdev.blog.api.controller.dto.TagsDTO;
import it.rdev.blog.api.dao.entity.Tags;
import it.rdev.blog.api.service.BlogTagsDetailService;

@RestController
public class TagsController {
	@Autowired
	private BlogTagsDetailService blogtags;
	

	
	@GetMapping("/api/tags")
	public ResponseEntity<?> AllTagsa()
	{
		Set<TagsDTO> tags =blogtags.findAll();
		if(tags!=null && tags.size()!=0) {
			return ResponseEntity.ok(tags);
		}else 
		{
			return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		

	}
		

}
