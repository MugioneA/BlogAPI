package it.rdev.blog.api.controller;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.rdev.blog.api.config.JwtTokenUtil;
import it.rdev.blog.api.controller.dto.ArticoloDTO;
import it.rdev.blog.api.controller.dto.TagsDTO;
import it.rdev.blog.api.controller.dto.UserDTO;
import it.rdev.blog.api.dao.entity.Articolo;
import it.rdev.blog.api.dao.entity.User;
import it.rdev.blog.api.service.BlogArticoloDatailService;
import it.rdev.blog.api.service.BlogUserDetailsService;



@RestController
public class ArticoloController {
	
	@Autowired
	private BlogArticoloDatailService blogarticolo;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private BlogUserDetailsService userDetailsService;
	
	
	//E' STATO IMPLEMENTATO L'UTILIZZO DEL  L'ID ATTRAVERSO IL TOKEN ANCHE SE NON E' NECESSARIO IN QUANTO LO USERNAME E' UNIVOCO QUINDI CAPACE DI IDENTIFICARE UNIVOCAMENTE IL NOSTRO UTENTE
	
	
	// il metodo serve per ricercare un articolo per categoria
	@GetMapping({ "/api/articolicat" })
public ResponseEntity<?> getAllArticoliCategoria(@RequestParam(name= "categoria",required = true) final String categoria) {
		Set<ArticoloDTO> art = blogarticolo.cercaPerCategoria(categoria);// ci creiamo un set di articoli per salvare il ritorno che ci da il metodo copn il parametro categoria salvato
		if(art!= null && art.size()>0) { // controllo se e' nullo
			return ResponseEntity.ok(blogarticolo.cercaPerCategoria(categoria));
		}else {
			return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
	// metodo per ricerca di un articolo partendo dall'username 
	@GetMapping({ "/api/articoliautore" })
public ResponseEntity<?> getAllArticoliAutore(@RequestParam(name= "autore",required = true) final String nome) {		
		Set<ArticoloDTO> art = blogarticolo.cercaPerAutore(nome);
		if(art!= null && art.size()>0) {
			return ResponseEntity.ok(blogarticolo.cercaPerAutore(nome));
		}else {
			return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	// controllo articolo per id 
	
	@GetMapping("/api/articolo/{id:\\d+}")
    public ResponseEntity<ArticoloDTO> restArtId(@PathVariable final Long id, @RequestHeader(name = "Authorization") String token ){
        ResponseEntity<ArticoloDTO> re = null;
        ArticoloDTO artDto = blogarticolo.findById(id);
        if(token != null && token.startsWith("Bearer")) {
            token = token.replaceAll("Bearer ", "");
            String username = jwtTokenUtil.getUsernameFromToken(token);
            if (artDto != null && artDto.getUser().getUsername().equals(username)) {  // controllimao che lo user del token e lo user dell'articolo che vuole cercare siano identici 
                re = new ResponseEntity<ArticoloDTO>(artDto, HttpStatus.OK);
            } else {
                re = new ResponseEntity<ArticoloDTO>(artDto, HttpStatus.NOT_FOUND);
            }
        } else {
            if(artDto != null && artDto.isStato()!= false) {
                re = new ResponseEntity<ArticoloDTO>(artDto, HttpStatus.OK);
            }else {
                re = new ResponseEntity<ArticoloDTO>(artDto, HttpStatus.NOT_FOUND);
            }
        }
        return re;
        
	}
	
	// creazione di un articolo 
	@PutMapping("/api/articolocreate")
	public ResponseEntity<?> articoloCreate(@RequestBody ArticoloDTO art, @RequestHeader(name = "Authorization") String token){
		if(token!= null && token.startsWith("Bearer")) {
			token = token.replaceAll("Bearer ", "");
            String username = jwtTokenUtil.getUsernameFromToken(token);
           User user = userDetailsService.findByUsername(username); // mi basta controllare che l' utente sia registrato per creare un qualsiasi articolo
			if(user!= null) { // controllo se e' nullo
				blogarticolo.saves(art);		// chiamo il metodo per fare l'insedrt	
				return new  ResponseEntity<>(HttpStatus.OK);
			}
			else 
			{
				return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		}
		return null;
		
	}
	// cancello l'articolo tramite id
	@DeleteMapping("/api/articolodelete/{id:\\d+}")
	public ResponseEntity<?> articoloDelete(@PathVariable final Long id, @RequestHeader(name = "Authorization") String token ){
		if(token!= null && token.startsWith("Bearer")) {
			token = token.replaceAll("Bearer ", "");
            Long iduser = jwtTokenUtil.getUserIdFromToken(token);
            if(blogarticolo.Delete(id, iduser)) {  // passo il parametro dell'id articolo da cancellare e lid del token dell'utente che vuole cancellare
            	return new  ResponseEntity<>(HttpStatus.OK);
            
		}
	
	}
		return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
	
}
	
	// ricerca di un articolo per testo sottotitolo op titolo
	@PostMapping("/api/articolotesto")
	public ResponseEntity<?> ricercaPerTitoloSottotitoloTesto(@RequestParam(name= "parola",required = true) final String parola, @RequestHeader(name = "Authorization") String token ){
		if(token!= null && token.startsWith("Bearer")) {
			token = token.replaceAll("Bearer ", "");
			 String username = jwtTokenUtil.getUsernameFromToken(token);
			 User user = userDetailsService.findByUsername(username);
			 if(user!=null) {
				 Set<ArticoloDTO> art = blogarticolo.findByTesto(parola);
				 if(art!=null && art.size()>0) { // controllo che il mio set di articoli non sia vuoto
					 return ResponseEntity.ok(blogarticolo.findByTesto(parola));  // ritorno del set di articoli
				 }else
				 {
					 return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
				 }
			 }
			 else {
				 return new  ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			 }
}
		return null;
}
	
	// metodo che fa l'update dell'articolo scelto tramite id
	@PutMapping("/api/articoloupdate")
    public ResponseEntity<?> update(@RequestParam(name= "id",required = true) final Long id,@RequestBody ArticoloDTO art, @RequestHeader(name = "Authorization") String token ){
			if(token!= null && token.startsWith("Bearer")) {
			token = token.replaceAll("Bearer ", "");
			 Long iduser = jwtTokenUtil.getUserIdFromToken(token);
			if(blogarticolo.update(id, art, iduser)) {
				return new  ResponseEntity<>(HttpStatus.OK);
			}
			return new  ResponseEntity<>(HttpStatus.NOT_FOUND);		
	}
			return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);		
}

	@PostMapping("/api/articolostato")
    public ResponseEntity<?> CercaArticoloPerStato(@RequestParam(name= "stato",required = true) final Long valore,@RequestHeader(name = "Authorization") String token ){
			if(token!= null && token.startsWith("Bearer")) {
			token = token.replaceAll("Bearer ", "");
			 String username = jwtTokenUtil.getUsernameFromToken(token);
			 User user = userDetailsService.findByUsername(username);
			 Long iduser = jwtTokenUtil.getUserIdFromToken(token);
			 if(user!=null) {
				 Set<ArticoloDTO> art = blogarticolo.ricercaPerStato(valore,iduser);  
				 if(art!=null && art.size()>0) { // controllo che il mio set di articoli non sia vuoto
					 return ResponseEntity.ok(blogarticolo.ricercaPerStato(valore,iduser));  // ritorno del set di articoli
				 }else
				 {
					 return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
				 }
			 }
			 else {
				 return new  ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			 }
	
			}
			return null;
}
}


