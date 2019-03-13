package com.br.pan.controller;

import com.br.pan.exception.ExcecaoDadosCadastrais;
import com.br.pan.model.Cliente;
import com.br.pan.model.Endereco;
import com.br.pan.model.Estado;
import com.br.pan.model.Municipio;
import com.br.pan.repository.RepositorioCliente;
import com.br.pan.service.ServicoDadosCadastrais;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControladorDadosCadastrais {

	private static final Logger log = LoggerFactory.getLogger(ControladorDadosCadastrais.class);

	@Autowired
	private ServicoDadosCadastrais servicoDadosCadastrais;

	@Autowired
	private RepositorioCliente repositorioCliente;

    @RequestMapping(value = "/cliente/{cpf}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> buscaClientePorCpf(@PathVariable("cpf") String cpf) throws ExcecaoDadosCadastrais{
		log.info("Buscando os dados cadastrais do cliente de CPF [{}]", cpf);
    	Cliente cliente = servicoDadosCadastrais.buscaClientePorCpf(cpf);
    	if (cliente == null || cliente.getId() <= 0){
            throw new ExcecaoDadosCadastrais("CPF [" + cpf + "] não encontrado");
    	}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

    @RequestMapping(value = "/cliente/{cpf}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<Cliente> atualizaEndereco (@RequestBody Endereco endereco, @PathVariable("cpf") String cpf) throws ExcecaoDadosCadastrais{
		log.info("Atualizando endereco do cliente de CPF [{}]", cpf);
    	Cliente cliente = servicoDadosCadastrais.buscaClientePorCpf(cpf);
    	if (cliente == null || cliente.getId() <= 0){
            throw new ExcecaoDadosCadastrais("CPF [" + cpf + "] não encontrado para atualização");
    	}
    	endereco.setId(cliente.getEndereco().getId());
		return new ResponseEntity<Cliente>(servicoDadosCadastrais.atualizaEndereco(cliente, endereco), HttpStatus.OK);
   	}

	@RequestMapping(value="/cep/{cep}", method=RequestMethod.GET)
	public ResponseEntity<Endereco> consultaCEP(@PathVariable("cep") String cep) throws ExcecaoDadosCadastrais{
		log.info("Obtendo o endereço referente ao CEP [{}]", cep);
		Endereco endereco = servicoDadosCadastrais.buscaEnderecoPeloCEP(cep);
		if (endereco == null){
			throw new ExcecaoDadosCadastrais("CEP ["+ cep + "] não encontrado");
		}
		return new ResponseEntity<Endereco>(endereco, HttpStatus.OK);
	}

	@RequestMapping(value="/estados", method=RequestMethod.GET)
	public ResponseEntity<List<Estado>> consultaEstados() throws ExcecaoDadosCadastrais{
		log.info("Obtendo lista de Estados");
		List<Estado> listaEstados = servicoDadosCadastrais.buscaEstados();
		return new ResponseEntity<List<Estado>>(listaEstados, HttpStatus.OK);
	}

	@RequestMapping(value="/municipios/{idEstado}", method=RequestMethod.GET)
	public ResponseEntity<List<Municipio>> consultaMunicipios(@PathVariable("idEstado") int idEstado) throws ExcecaoDadosCadastrais{
		log.info("Obtendo lista de Municípios do Estado de id {}", idEstado);
		List<Municipio> listaMunicipios = servicoDadosCadastrais.buscaMunicipios(idEstado);
		if (listaMunicipios == null || listaMunicipios.isEmpty()){
			throw new ExcecaoDadosCadastrais("Estado [" + idEstado + "] não encontrado");
		}
		return new ResponseEntity<List<Municipio>>(listaMunicipios, HttpStatus.OK);
	}

}
