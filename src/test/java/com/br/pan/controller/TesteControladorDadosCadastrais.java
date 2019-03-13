package com.br.pan.controller;

import com.br.pan.AplicacaoDadosCadastrais;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AplicacaoDadosCadastrais.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteControladorDadosCadastrais {

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;

	@Before
	public void configuracao() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testeBuscaClientePorCPF() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/cliente/10020030088").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.cpf").exists())
		.andExpect(jsonPath("$.endereco.rua").exists())
		.andExpect(jsonPath("$.endereco.numero").exists())
		.andExpect(jsonPath("$.endereco.complemento").exists())
		.andExpect(jsonPath("$.endereco.cep").exists())
		.andExpect(jsonPath("$.endereco.bairro").exists())
		.andExpect(jsonPath("$.endereco.estado").exists())
		.andExpect(jsonPath("$.endereco.municipio").exists())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.cpf").value("10020030088"))
		.andExpect(jsonPath("$.endereco.rua").value("Rua XPTO"))
		.andExpect(jsonPath("$.endereco.numero").value("100"))
		.andExpect(jsonPath("$.endereco.complemento").value("Apt 123"))
		.andExpect(jsonPath("$.endereco.cep").value("01310940"))
		.andExpect(jsonPath("$.endereco.bairro").value("Bela Vista"))
		.andExpect(jsonPath("$.endereco.estado.nome").value("São Paulo"))
		.andExpect(jsonPath("$.endereco.municipio.nome").value("São Paulo"))
		.andDo(print());
	}
	
	@Test
	public void testBuscaClienteNaoCadastradoPorCPF() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/cliente/4").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.codigoErro").value(404))
		.andExpect(jsonPath("$.mensagem").value("CPF [4] não encontrado"))
		.andDo(print());
	}
	
	@Test
	public void testeDeAtualizacaoEndereco() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.patch("/cliente/10020030088")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"id\":1,\"rua\":\"Rua ZZZ\",\"numero\":\"200\",\"complemento\":\"Apt 123\",\"bairro\":\"Bela Vista\",\"cep\":\"01310940\",\"municipio\":{\"id\":1,\"nome\":\"São Paulo\",\"estado\":{\"id\":1,\"nome\":\"São Paulo\"}},\"estado\":{\"id\":1,\"nome\":\"São Paulo\"}}")
        .accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.cpf").exists())
		.andExpect(jsonPath("$.endereco.rua").exists())
		.andExpect(jsonPath("$.endereco.numero").exists())
		.andExpect(jsonPath("$.endereco.complemento").exists())
		.andExpect(jsonPath("$.endereco.cep").exists())
		.andExpect(jsonPath("$.endereco.bairro").exists())
		.andExpect(jsonPath("$.endereco.estado").exists())
		.andExpect(jsonPath("$.endereco.municipio").exists())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.cpf").value("10020030088"))
		.andExpect(jsonPath("$.endereco.rua").value("Rua ZZZ"))
		.andExpect(jsonPath("$.endereco.numero").value("200"))
		.andExpect(jsonPath("$.endereco.complemento").value("Apt 123"))
		.andExpect(jsonPath("$.endereco.cep").value("01310940"))
		.andExpect(jsonPath("$.endereco.bairro").value("Bela Vista"))
		.andExpect(jsonPath("$.endereco.estado.nome").value("São Paulo"))
		.andExpect(jsonPath("$.endereco.municipio.nome").value("São Paulo"))
		.andDo(print());
	}
	
	@Test
	public void testeDeAtualizacaoEnderecoDeClienteNaoEncontrado() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.patch("/cliente/97248413089")
        .contentType(MediaType.APPLICATION_JSON)
		.content("{\"id\":1,\"rua\":\"Rua ZZZ\",\"numero\":\"200\",\"complemento\":\"Apt 123\",\"bairro\":\"Bela Vista\",\"cep\":\"01310940\",\"municipio\":{\"id\":1,\"nome\":\"São Paulo\",\"estado\":{\"id\":1,\"nome\":\"São Paulo\"}},\"estado\":{\"id\":1,\"nome\":\"São Paulo\"}}")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.codigoErro").value(404))
		.andExpect(jsonPath("$.mensagem").value("CPF [97248413089] não encontrado para atualização"))
		.andDo(print());
	}

}