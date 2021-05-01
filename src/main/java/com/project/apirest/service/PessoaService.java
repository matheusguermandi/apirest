package com.project.apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.apirest.domain.Pessoa;
import com.project.apirest.domain.dto.PessoaDto;
import com.project.apirest.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	public PessoaDto create(Pessoa pessoa) {
		PessoaDto pessoaDto = new PessoaDto();
		pessoaDto.setPessoa(repository.save(pessoa));
		
		return pessoaDto;
	}

}
