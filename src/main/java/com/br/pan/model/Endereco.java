package com.br.pan.model;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;

@Entity
@Table(name="endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@SerializedName("logradouro")
	@Column(name = "rua")
	private String rua;
	@Column(name = "numero")
	private String numero;
	@Column(name = "complemento")
	private String complemento;
	@Column(name = "bairro")
	private String bairro;
	@Column(name = "cep")
	private String cep;
	@SerializedName("localidade")
	@JoinColumn(name = "municipio_id")
	@ManyToOne
	private Municipio municipio;
	@JoinColumn(name = "estado_id")
	@ManyToOne
	private Estado estado;

	public Endereco() {
		super();
	}

	public Endereco(long id, String rua, String numero, String complemento, String bairro, String cep, Municipio municipio, Estado estado) {
		super();
		this.id = id;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.municipio = municipio;
		this.estado = estado;
	}

	public Endereco(String rua, String numero, String complemento, String bairro, String cep, Municipio municipio, Estado estado) {
		super();
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.municipio = municipio;
		this.estado = estado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		if(cep != null) {
			this.cep = cep;
		}
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		if(rua != null){
			this.rua = rua;
		}
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		if(numero != null) {
			this.numero = numero;
		}
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		if(complemento != null) {
			this.complemento = complemento;
		}
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		if(bairro != null) {
			this.bairro = bairro;
		}
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		if(municipio != null){
			this.municipio = municipio;
		}
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		if(estado != null){
			this.estado = estado;
		}
	}

}