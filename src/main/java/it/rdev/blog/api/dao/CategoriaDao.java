package it.rdev.blog.api.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.rdev.blog.api.dao.entity.Categoria;


@Repository
public interface CategoriaDao extends CrudRepository<Categoria, String> {
	
	@Query("Select a from Categoria a")
	Set<Categoria> findAll();

}
