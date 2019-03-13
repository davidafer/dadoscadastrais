package com.br.pan.model;

import javax.persistence.*;

@Entity
@Table(name="estado")
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "nome", unique = true, nullable = false)
	private String nome;

	public Estado() {
		super();
	}

	public Estado(long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Estado(String nome) {
		super();
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}