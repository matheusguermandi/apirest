package com.project.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.apirest.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
