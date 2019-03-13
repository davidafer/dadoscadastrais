package com.br.pan.exception;

import com.br.pan.bean.RespostaErro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GerenciadorExcecao {

	private static final Logger log = LoggerFactory.getLogger(GerenciadorExcecao.class);

	@ExceptionHandler(ExcecaoDadosCadastrais.class)
	public ResponseEntity<RespostaErro> gerenciaExcecaoDadosCadastrais(Exception e) {
		log.error("Erro: {}", e.getMessage());
		RespostaErro respostaErro = new RespostaErro();
		respostaErro.setCodigoErro(HttpStatus.NOT_FOUND.value());
		respostaErro.setMensagem(e.getMessage());
		return new ResponseEntity<RespostaErro>(respostaErro, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<RespostaErro> gerenciaExcecaoGenerica(Exception e) {
		log.error("Erro: ", e);
		RespostaErro respostaErro = new RespostaErro();
		respostaErro.setCodigoErro(HttpStatus.BAD_REQUEST.value());
		respostaErro.setMensagem("Erro no processamento dos dados");
		return new ResponseEntity<RespostaErro>(respostaErro, HttpStatus.BAD_REQUEST);
	}

}
