package it.rdev.blog.api.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rdev.blog.api.controller.dto.TagsDTO;
import it.rdev.blog.api.dao.TagsDao;
import it.rdev.blog.api.dao.entity.Tags;

@Service
public class BlogTagsDetailService {

	@Autowired
	private TagsDao tagsDao;
	
	public Set<TagsDTO> findAll(){
		Set<Tags> listas = tagsDao.findAll();
		Set<TagsDTO> lista = new HashSet();
		for(Tags t: listas) {
			TagsDTO ts = new TagsDTO();
			ts.setTag(t.getTag());
		}
		
		return lista;
		
	}
	

	
	
}
