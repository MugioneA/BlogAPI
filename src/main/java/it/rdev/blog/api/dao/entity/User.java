package it.rdev.blog.api.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "utente")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	@Column(name="username")
	private String username;
	@Column(name="password")
	@JsonIgnore
	private String password;
	
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private Set<Articolo> articolo;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<Articolo> getArticolo() {
		return articolo;
	}

	public void setArticolo(Set<Articolo> articolo) {
		this.articolo = articolo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}