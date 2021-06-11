package it.rdev.blog.api.dao.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {

	@Id
	@Column( name = "categorias", length = 25 )
	private String categorias;
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	private Set<Articolo> articolo;

	public Set<Articolo> getArticolo() {
		return articolo;
	}

	public void setArticolo(Set<Articolo> articolo) {
		this.articolo = articolo;
	}

	public String getCategorias() {
		return categorias;
	}

	public void setCategorias(String categorias) {
		this.categorias = categorias;
	}
	
}
