package com.br.pan.service;

import com.br.pan.model.Cliente;
import com.br.pan.model.Endereco;
import com.br.pan.model.Estado;
import com.br.pan.model.Municipio;

import java.util.List;

public interface ServicoDadosCadastrais {

	public List<Cliente> buscaClientes();

	public List<Estado> buscaEstados();

	public List<Municipio> buscaMunicipios(int idEstado);

	public Cliente buscaClientePorCpf(String cpf);

	public Cliente atualizaEndereco(Cliente cliente, Endereco endereco);

	public Endereco buscaEnderecoPeloCEP(String cep);

}
