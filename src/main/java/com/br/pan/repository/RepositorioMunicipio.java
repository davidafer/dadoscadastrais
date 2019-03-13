package com.br.pan.repository;

import com.br.pan.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("repositorioMunicipio")
public interface RepositorioMunicipio extends JpaRepository<Municipio, Long>{

}