package it.rdev.blog.api.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.rdev.blog.api.dao.entity.Tags;

@Repository
public interface TagsDao extends CrudRepository<Tags, String> {
	
	@Query("Select a from Tags a")
	Set<Tags> findAll();

}
