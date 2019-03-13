package com.br.pan.bean;

public class Resposta {

	private int status;
	private String mensagem;

	public Resposta() {
		super();
	}

	public Resposta(int status, String mensagem) {
		super();
		this.status = status;
		this.mensagem = mensagem;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
