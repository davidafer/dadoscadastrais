package com.br.pan.repository;

import com.br.pan.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("repositorioEstado")
public interface RepositorioEstado extends JpaRepository<Estado, Long>{

}