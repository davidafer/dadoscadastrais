package com.br.pan.repository;

import com.br.pan.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("repositorioEndereco")
public interface RepositorioEndereco extends JpaRepository<Endereco, Long>{

}