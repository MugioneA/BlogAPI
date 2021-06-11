package it.rdev.blog.api.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rdev.blog.api.controller.dto.CategoriaDTO;
import it.rdev.blog.api.dao.CategoriaDao;
import it.rdev.blog.api.dao.entity.Categoria;

@Service
public class BlogCategoriaDetailService {
	
	@Autowired
	private CategoriaDao categoriaDao;
	
	public Set<CategoriaDTO> find(){
		Set<Categoria> listacat = categoriaDao.findAll();
		Set<CategoriaDTO> lista = new HashSet();
		
		for(Categoria cat : listacat) {
			CategoriaDTO catdto = new CategoriaDTO();
			catdto.setCategoria(cat.getCategorias());
			lista.add(catdto);
		}
		return lista;
	}



}
