package com.br.pan.model;

import javax.persistence.*;

@Entity
@Table(name="municipio")
public class Municipio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "nome", nullable = false)
	private String nome;
	@JoinColumn(name = "estado_id")
	@ManyToOne
	private Estado estado;

	public Municipio() {
		super();
	}

	public Municipio(long id, String nome, Estado estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.estado = estado;
	}

	public Municipio(String nome, Estado estado) {
		super();
		this.nome = nome;
		this.estado = estado;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}