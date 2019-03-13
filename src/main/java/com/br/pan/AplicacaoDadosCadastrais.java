package com.br.pan;

import com.br.pan.model.Cliente;
import com.br.pan.model.Endereco;
import com.br.pan.model.Estado;
import com.br.pan.model.Municipio;
import com.br.pan.repository.RepositorioCliente;
import com.br.pan.repository.RepositorioEndereco;
import com.br.pan.repository.RepositorioEstado;
import com.br.pan.repository.RepositorioMunicipio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AplicacaoDadosCadastrais {
	
	private static final Logger log = LoggerFactory.getLogger(AplicacaoDadosCadastrais.class);

	public static void main(String[] args) {
		SpringApplication.run(AplicacaoDadosCadastrais.class, args);
	}
	
	@Bean
	public CommandLineRunner init(RepositorioCliente repositorioCliente, RepositorioEndereco repositorioEndereco, RepositorioMunicipio repositorioMunicipio, RepositorioEstado repositorioEstado) {
		log.info("Inserindo clientes na base de dados");

		return (args) -> {
			Estado estado = repositorioEstado.save(new Estado("São Paulo"));
			Municipio municipio = repositorioMunicipio.save(new Municipio("São Paulo", estado));

			Endereco endereco1 = repositorioEndereco.save(new Endereco("Rua XPTO", "100", "Apt 123", "Bela Vista","01310940", municipio, estado));
			repositorioCliente.save(new Cliente("10020030088", endereco1));

			Endereco endereco2 = repositorioEndereco.save(new Endereco("Rua ABC", "200", "Apt 1C", "Bela Vista","01311936", municipio, estado));
			repositorioCliente.save(new Cliente("20367882388", endereco2));

			Endereco endereco3 = repositorioEndereco.save(new Endereco("Rua XYZ", "300", "Bloco 1 Apt 17", "Bela Vista","01310915", municipio, estado));
			repositorioCliente.save(new Cliente("89837721073", endereco3));

			Endereco endereco4 = repositorioEndereco.save(new Endereco("Rua AEIOU", "40A", "Casa 2", "Casa Verde","02515000", municipio, estado));
			repositorioCliente.save(new Cliente("27219187530", endereco4));

			Endereco endereco5 = repositorioEndereco.save(new Endereco("Rua AAA", "500", "Apt 145", "Casa Verde","02512050", municipio, estado));
			repositorioCliente.save(new Cliente("74812553261", endereco5));

			log.info("Clientes inseridos na base de dados");
		};
	}

}