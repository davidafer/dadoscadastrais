package com.br.pan.exception;

public class ExcecaoDadosCadastrais extends Exception {

	private static final long serialVersionUID = 1L;
	private String mensagemErro;

	public ExcecaoDadosCadastrais() {
		super();
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public ExcecaoDadosCadastrais(String mensagemErro) {
		super(mensagemErro);
		this.mensagemErro = mensagemErro;
	}

}