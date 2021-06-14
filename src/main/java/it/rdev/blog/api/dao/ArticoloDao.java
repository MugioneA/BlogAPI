package it.rdev.blog.api.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.rdev.blog.api.dao.entity.Articolo;

@Repository
public interface ArticoloDao extends CrudRepository<Articolo, Integer> {

	//Set<Articolo> findByAutore(String username);
	@Query("Select a from Articolo a join a.categoria c where c.categorias=:categoria" )
	Set<Articolo> findpercategoria(@Param("categoria") String categoria);
	@Query("Select a from Articolo a join a.users u where u.username=:nome" )
	Set<Articolo> fidperautore(@Param("nome") String nome);
	@Query("Select a from Articolo a where a.titolo like :titolo OR a.sottotitolo like :titolo OR a.testo like :titolo")
	//Set<Articolo> findByTitoloOrSottotitoloOrTesto(String titolo, String sottotilo, String testo);	
	Set<Articolo> ricercaPerTesto(@Param("titolo") String titolo);
	//@Query("Select a from Articolo a where a.id=:id")
	Articolo findById(long id);
//	@Query("Select a from Articolo a")
	//Set<Articolo> findAll();
	//Set<Articolo> findByTag(String tag);

	
	@Query("Delete from Articolo a where a.id=:id")
	void DeleteById(@Param("id")  long id);
	
	@Query("Select a from Articolo a where a.stato=:valore")
	Set<Articolo> findByStato(@Param("valore")  long value);
	
}
