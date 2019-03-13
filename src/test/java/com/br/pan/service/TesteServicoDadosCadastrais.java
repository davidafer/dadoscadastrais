package com.br.pan.service;

import com.br.pan.model.Cliente;
import com.br.pan.model.Endereco;
import com.br.pan.model.Estado;
import com.br.pan.model.Municipio;
import com.br.pan.repository.RepositorioCliente;
import com.br.pan.repository.RepositorioEndereco;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class TesteServicoDadosCadastrais {
	
	@Mock
	private RepositorioCliente repositorioCliente;

	@Mock
	private RepositorioEndereco repositorioEndereco;

	@InjectMocks
	private ServicoDadosCadastraisImpl servicoDadosCadastraisImpl;
	
	@Before
	public void configuracao(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testeAtualizacaoEndereco(){
		Estado estado = new Estado(1,"São Paulo");
		Municipio municipio = new Municipio(1,"São Paulo", estado);
		Endereco endereco = new Endereco(1,"Rua ZZZ", "100", "Apt 123", "Bela Vista","01310940", municipio, estado);
		Cliente cliente = new Cliente(1,"10020030088", endereco);

		when(repositorioEndereco.save(cliente.getEndereco())).thenReturn(cliente.getEndereco());
		when(repositorioCliente.save(cliente)).thenReturn(cliente);
		Cliente resultado = servicoDadosCadastraisImpl.atualizaEndereco(cliente, endereco);
		assertEquals("10020030088", resultado.getCpf());
		assertEquals("Rua ZZZ", resultado.getEndereco().getRua());
		assertEquals("100", resultado.getEndereco().getNumero());
		assertEquals("Apt 123", resultado.getEndereco().getComplemento());
		assertEquals("Bela Vista", resultado.getEndereco().getBairro());
		assertEquals("01310940", resultado.getEndereco().getCep());
		assertEquals("São Paulo", resultado.getEndereco().getMunicipio().getNome());
		assertEquals("São Paulo", resultado.getEndereco().getEstado().getNome());
	}

	@Test
	public void testeConsultaCEP(){
		Endereco resultado = servicoDadosCadastraisImpl.buscaEnderecoPeloCEP("01310940");
		assertEquals("Avenida Paulista", resultado.getRua());
		assertEquals("Bela Vista", resultado.getBairro());
		assertEquals("01310940", resultado.getCep());
		assertEquals("São Paulo", resultado.getMunicipio().getNome());
		assertEquals("SP", resultado.getEstado().getNome());
	}
	
}