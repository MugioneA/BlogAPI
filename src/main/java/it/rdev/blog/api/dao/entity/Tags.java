package it.rdev.blog.api.dao.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "tags")
public class Tags {
	
	public Set<Articolo> getArticolo() {
		return articolo;
	}

	public void setArticolo(Set<Articolo> articolo) {
		this.articolo = articolo;
	}

	@Id
	@Column( name = "tag", length = 25 )
	private String tag;
	
	
	@ManyToMany
	@JoinTable(
	  name = "tags_articolo", 
	  joinColumns = @JoinColumn(name = "id_tag"), 
	  inverseJoinColumns = @JoinColumn(name = "id_articolo"))
	private Set<Articolo> articolo;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
