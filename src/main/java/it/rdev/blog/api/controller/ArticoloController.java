package it.rdev.blog.api.controller;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.rdev.blog.api.config.JwtTokenUtil;
import it.rdev.blog.api.controller.dto.ArticoloDTO;
import it.rdev.blog.api.controller.dto.TagsDTO;
import it.rdev.blog.api.controller.dto.UserDTO;
import it.rdev.blog.api.dao.entity.Articolo;
import it.rdev.blog.api.service.BlogArticoloDatailService;



@RestController
public class ArticoloController {
	
	@Autowired
	private BlogArticoloDatailService blogarticolo;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	//@GetMapping({ "/api/articoliall" })
//	public ResponseEntity<?> getAllArticolir() {
	//	
	//	return ResponseEntity.ok(blogarticolo.find());
	//}
	
	@GetMapping({ "/api/articolicat" })
public ResponseEntity<?> getAllArticoliCategoria(@RequestParam(name= "categoria",required = true) final String categoria) {
		Set<ArticoloDTO> art = blogarticolo.cercaPerCategoria(categoria);
		if(art!= null && art.size()>0) {
			return ResponseEntity.ok(blogarticolo.cercaPerCategoria(categoria));
		}else {
			return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	@GetMapping({ "/api/articoliautore" })
public ResponseEntity<?> getAllArticoliAutore(@RequestParam(name= "autore",required = true) final String nome) {
		
		
		Set<ArticoloDTO> art = blogarticolo.cercaPerAutore(nome);
		if(art!= null && art.size()>0) {
			return ResponseEntity.ok(blogarticolo.cercaPerAutore(nome));
		}else {
			return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/api/articolo/{id:\\d+}")
    public ResponseEntity<ArticoloDTO> restArtId(@PathVariable final Long id, @RequestHeader(name = "Authorization") String token ){
        ResponseEntity<ArticoloDTO> re = null;
        ArticoloDTO artDto = blogarticolo.findById(id);
        if(token != null && token.startsWith("Bearer")) {
            token = token.replaceAll("Bearer ", "");
            String username = jwtTokenUtil.getUsernameFromToken(token);
            if (artDto != null && artDto.getUser().getUsername().equals(username)) {
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
	
	@PostMapping("/api/articolocreate")
	public ResponseEntity<?> articoloCreate(@RequestBody ArticoloDTO art, String token){
		if(token!= null && token.startsWith("Bearer")) {
			
		}
		
		return null;
		
	}
	
}


