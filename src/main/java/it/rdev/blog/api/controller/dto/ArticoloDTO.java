package it.rdev.blog.api.controller.dto;

import java.time.LocalDateTime;

import it.rdev.blog.api.dao.entity.Categoria;
import it.rdev.blog.api.dao.entity.User;

public class ArticoloDTO {
	
	private Integer id;
	private String titolo;
	private String sottotitolo;
	private String testo;
	private boolean stato;
	private LocalDateTime datacreaz;
	private LocalDateTime datapub;
	private LocalDateTime datamod;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	private User user;
	private Categoria categoria;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getSottotitolo() {
		return sottotitolo;
	}
	public void setSottotitolo(String sottotitolo) {
		this.sottotitolo = sottotitolo;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public boolean isStato() {
		return stato;
	}
	public void setStato(boolean stato) {
		this.stato = stato;
	}
	public LocalDateTime getDatacreaz() {
		return datacreaz;
	}
	public void setDatacreaz(LocalDateTime datacreaz) {
		this.datacreaz = datacreaz;
	}
	public LocalDateTime getDatapub() {
		return datapub;
	}
	public void setDatapub(LocalDateTime datapub) {
		this.datapub = datapub;
	}
	public LocalDateTime getDatamod() {
		return datamod;
	}
	public void setDatamod(LocalDateTime datamod) {
		this.datamod = datamod;
	}

}
