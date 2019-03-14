package com.lscon.listacompras.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Lista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;

	@JsonIgnore
	@OneToMany(mappedBy = "lista", cascade = CascadeType.ALL)
	private Set<Produto> produtos;
	
	
	//Getters and Setters
	
	public Long getId() {
		return id;
	}
	public Set<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
