package it.rdev.blog.api.service;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import it.rdev.blog.api.controller.dto.ArticoloDTO;
import it.rdev.blog.api.dao.ArticoloDao;
import it.rdev.blog.api.dao.entity.Articolo;

@Service
public class BlogArticoloDatailService {

	@Autowired
	private ArticoloDao articoloDao;
	
	public Set<ArticoloDTO> cercaPerCategoria(String categories){
		Set<Articolo> listaArticolo = articoloDao.findpercategoria(categories);
		Set<ArticoloDTO> lista = new HashSet();
		
		for(Articolo art: listaArticolo) {
			ArticoloDTO artDto = new ArticoloDTO();
			artDto.setTitolo(art.getTitolo());
			artDto.setTesto(art.getTesto());
			artDto.setSottotitolo(art.getSottotitolo());
			artDto.setStato(art.isStato());
			artDto.setDatacreaz(art.getDatacreaz());
			artDto.setDatamod(art.getDatamod());
			artDto.setDatapub(art.getDatapub());
			lista.add(artDto);
			
		}
		
		
		return lista;
		
	}
	
	public Set<ArticoloDTO> cercaPerAutore(String nome){
		Set<Articolo> listaArticolo = articoloDao.findpercategoria(nome);
		Set<ArticoloDTO> lista = new HashSet();
		
		for(Articolo art: listaArticolo) {
			ArticoloDTO artDto = new ArticoloDTO();
			artDto.setTitolo(art.getTitolo());
			artDto.setTesto(art.getTesto());
			artDto.setSottotitolo(art.getSottotitolo());
			artDto.setStato(art.isStato());
			artDto.setDatacreaz(art.getDatacreaz());
			artDto.setDatamod(art.getDatamod());
			artDto.setDatapub(art.getDatapub());
			lista.add(artDto);
			
		}
		
		
		return lista;
		
	}
	public ArticoloDTO findById(@RequestBody long id){
        Articolo art = articoloDao.findById(id);
        ArticoloDTO artDto = new ArticoloDTO();
        if (art.isStato()!= false) {
        	artDto.setUser(art.getUsers());
        	artDto.setTitolo(art.getTitolo());
			artDto.setTesto(art.getTesto());
			artDto.setSottotitolo(art.getSottotitolo());
			artDto.setStato(art.isStato());
			artDto.setDatacreaz(art.getDatacreaz());
			artDto.setDatamod(art.getDatamod());
			artDto.setDatapub(art.getDatapub());
			artDto.setCategoria(art.getCategoria());

        }
        return artDto;
	

}
	public void save(ArticoloDTO art) {
		
	}
}
