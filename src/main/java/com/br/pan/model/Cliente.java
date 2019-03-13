package com.br.pan.model;

import javax.persistence.*;

@Entity
@Table(name="cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "cpf", unique = true, nullable = false)
	private String cpf;
	@JoinColumn(name = "endereco_id")
	@OneToOne
	private Endereco endereco;

	public Cliente() {
		super();
	}

	public Cliente(long id, String cpf, Endereco endereco) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.endereco = endereco;
	}

	public Cliente(String cpf, Endereco endereco) {
		super();
		this.cpf = cpf;
		this.endereco = endereco;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}