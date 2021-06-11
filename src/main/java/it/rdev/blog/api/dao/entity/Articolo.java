package it.rdev.blog.api.dao.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




@Entity
@Table(name = "articolo")
public class Articolo {

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
	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}


	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Set<Tags> getTag() {
		return tag;
	}

	public void setTag(Set<Tags> tag) {
		this.tag = tag;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name="titolo",nullable = false,length = 30)
	private String titolo;
	
	@Column(name="sottotitolo",length = 30)
	private String sottotitolo;
	
	@Column(name="testo",nullable = false)
	private String testo;
	
	@Column(name="stato",nullable = false)
	private boolean stato;
	
	@Column(name="datacreaz",nullable = false)
	private LocalDateTime datacreaz;
	
	@Column(name="datapub")
	private LocalDateTime datapub;
	
	@Column(name="datamod")
	private LocalDateTime datamod;
	
	
	@ManyToOne()
	@JoinColumn(name = "id_autore", referencedColumnName="id")
	private User users;
	
	@ManyToOne()
	@JoinColumn(name = "id_categoria", referencedColumnName="categorias")
	private Categoria categoria;
	
	@ManyToMany( mappedBy = "articolo" )
	private Set<Tags> tag;
	
}
