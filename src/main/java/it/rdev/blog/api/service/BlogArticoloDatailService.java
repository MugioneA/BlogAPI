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
	
	
	// ritorno di un set di articoli per una determinata categoria
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
	
	
	// ritorno di un set di articoli per un determinato autore
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
	
	// ritorno di un  articolo per un determinato id
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
	
	// metodo che permette l'insert di un articolo con i valori di un request body passato dal controller
	public void saves(ArticoloDTO art) {
		Articolo arti = new Articolo();
		arti.setTesto(art.getTesto());
		arti.setCategoria(art.getCategoria());
		arti.setDatacreaz(art.getDatacreaz());
		arti.setDatapub(art.getDatapub());
		arti.setDatamod(art.getDatamod());
		arti.setSottotitolo(art.getSottotitolo());
		arti.setStato(art.isStato());
		arti.setTag(art.getTag());
		arti.setUsers(art.getUser());
		articoloDao.save(arti);
	}
	public void savesB(ArticoloDTO art) {
		Articolo arti = new Articolo();
		arti.setTesto(art.getTesto());
		arti.setCategoria(art.getCategoria());
		arti.setDatacreaz(art.getDatacreaz());
		arti.setDatapub(art.getDatapub());
		arti.setDatamod(art.getDatamod());
		arti.setSottotitolo(art.getSottotitolo());
		arti.setStato(false);
		arti.setTag(art.getTag());
		arti.setUsers(art.getUser());
		articoloDao.save(arti);
	}
	
	// metodo che permette il delete di un articolo controllando che chi lo vuole cancellare ne sia il propietraio
	public boolean Delete(final Long id,final Long iduser) {
		Articolo art = articoloDao.findById(id);
		Long sid =art.getUsers().getId();
		if(sid.equals(iduser)) {
			articoloDao.DeleteById(id);;
			return true;
		}
	
		return false;
		
	}
	
	// metodo che restituisce un set di articoli data una parola che ricerchera' nel testo sottotitolo e titolo
	public Set<ArticoloDTO> findByTesto(String parola){
        Set<Articolo> listaArticolo = articoloDao.ricercaPerTesto(parola);
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
	
	//metodo per l'update
	public boolean update(final Long id, ArticoloDTO art,final Long iduser) {
		Articolo arti = articoloDao.findById(id);
		Long sid =art.getUser().getId();
		if(sid.equals(iduser)) {
		arti.setTesto(art.getTesto());
		arti.setCategoria(art.getCategoria());
		arti.setDatapub(art.getDatapub());
		arti.setDatamod(art.getDatamod());
		arti.setSottotitolo(art.getSottotitolo());
		arti.setStato(art.isStato());
		arti.setTag(art.getTag());
		articoloDao.save(arti);	
		return true;
		}
		return false;
	}
	// ritorno degli articoli con un determinato stato
	public Set<ArticoloDTO> ricercaPerStato(final Long valore,final Long iduser) {
		  Set<Articolo> listaArticolo = articoloDao.findByStato(valore);
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
				if(art.isStato()==true || valore!=0) { // controllo che lo stato dell'articolo sia true ovvero pubblicato quindi non c'e' bisogno di sapere che chi ha scritto l'articolo sia l'autore e conbtrollo anche che la sua ricerca 
				lista.add(artDto);                    // nonsia 0 ovvero false non pubblicato 
				}else
				{
					Long sid =art.getUsers().getId();   // se una delle due condizioni non si avvera significa che lo stato dell'articolo o e' in bozza oppure sta ricercanbdo un articolo per stato bozza quindi si procede nella verifica
					if(iduser.equals(sid)) {            // che chi vuole vedere l'articolo sia anche l'autore
						lista.add(artDto);
					}
				}
				
				
			}
	        return lista;
	}
}
