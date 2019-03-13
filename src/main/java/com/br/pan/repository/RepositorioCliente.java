package com.br.pan.repository;

import com.br.pan.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("repositorioCliente")
public interface RepositorioCliente extends JpaRepository<Cliente, Long>{

}