package com.br.pan.service;

import com.br.pan.model.Cliente;
import com.br.pan.model.Endereco;
import com.br.pan.model.Estado;
import com.br.pan.model.Municipio;
import com.br.pan.repository.RepositorioCliente;
import com.br.pan.repository.RepositorioEndereco;
import com.br.pan.repository.RepositorioEstado;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service("servicoDadosCadastrais")
public class ServicoDadosCadastraisImpl implements ServicoDadosCadastrais{

	@Autowired
	private RepositorioCliente repositorioCliente;

	@Autowired
	private RepositorioEndereco repositorioEndereco;

	@Autowired
	private RepositorioEstado repositorioEstado;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Cliente> buscaClientes() {
		return repositorioCliente.findAll();
	}

	@Override
	public Cliente buscaClientePorCpf(String cpf) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteria = criteriaBuilder.createQuery(Cliente.class);
		Root<Cliente> root = criteria.from(Cliente.class);
		criteria.select(root);
		criteria.where(criteriaBuilder.equal(root.get("cpf"), cpf));
		TypedQuery<Cliente> typed = entityManager.createQuery(criteria);
		List listaResultados = typed.getResultList();
		Cliente cliente = null;
		if(listaResultados.isEmpty()){
			return null;
		}
		return (Cliente) listaResultados.get(0);
	}

	@Override
	public Cliente atualizaEndereco(Cliente cliente, Endereco novoEndereco) {
		// Não sobrescreverá os valores do banco com valores nulos devido a validação no setters do bean
		cliente.getEndereco().setRua(novoEndereco.getRua());
		cliente.getEndereco().setNumero(novoEndereco.getNumero());
		cliente.getEndereco().setComplemento(novoEndereco.getComplemento());
		cliente.getEndereco().setBairro(novoEndereco.getBairro());
		cliente.getEndereco().setCep(novoEndereco.getCep());
		cliente.getEndereco().setMunicipio(novoEndereco.getMunicipio());
		cliente.getEndereco().setEstado(novoEndereco.getEstado());
		Endereco endereco = repositorioEndereco.save(cliente.getEndereco());
		cliente.setEndereco(endereco);
		return cliente;
	}

	@Override
	public Endereco buscaEnderecoPeloCEP(String cep) {
		try {
			String urlObtencaoCEP = "http://viacep.com.br/ws/"+ cep +"/json";
			URL url = new URL(urlObtencaoCEP);
			URLConnection urlConnection = url.openConnection();

			try(InputStream is = urlConnection.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))){

				StringBuilder jsonRetorno = new StringBuilder();
				bufferedReader.lines().forEach(l -> jsonRetorno.append(l.trim()));
				String json = jsonRetorno.toString()
						.replaceAll("[{},:]", "")
						.replaceAll("\"", "\n");

				String array[] = new String[30];
				array = json.split("\n");

				Endereco endereco = new Endereco();
				endereco.setRua(array[7]);
				endereco.setBairro(array[15]);
				endereco.setCep(cep);
				endereco.setMunicipio(new Municipio(array[19], new Estado(array[23])));
				endereco.setEstado(new Estado(array[23]));
				return endereco;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Estado> buscaEstados() {
		try {
			URL url = new URL("https://servicodados.ibge.gov.br/api/v1/localidades/estados");

			try(InputStream inputStrem = url.openStream();
				InputStreamReader reader = new InputStreamReader(inputStrem)){

				Type type = new TypeToken<ArrayList<Estado>>(){}.getType();
				List<Estado> listaEstados = new Gson().fromJson(reader, type);
				List<String> teste = new ArrayList<>();

				// São Paulo
				Collections.swap(listaEstados, 19, 0);
				// Rio de Janeiro
				Collections.swap(listaEstados, 18, 1);
				Collections.sort(listaEstados.subList(2, listaEstados.size()), Comparator.comparing(Estado::getNome));

				return listaEstados;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Municipio> buscaMunicipios(int idMunicipio) {
		try {
			URL url = new URL("https://servicodados.ibge.gov.br/api/v1/localidades/estados/" + idMunicipio + "/municipios");

			try(InputStream inputStrem = url.openStream();
				InputStreamReader reader = new InputStreamReader(inputStrem)){

				Type type = new TypeToken<ArrayList<Estado>>(){}.getType();
				List<Municipio> listaMunicipios = new Gson().fromJson(reader, type);
				return listaMunicipios;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}