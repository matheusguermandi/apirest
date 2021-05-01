package com.project.apirest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public Pessoa update(Pessoa novaPessoa, Long id) {
		Pessoa pessoa = findById(id);
		pessoa.setNome(novaPessoa.getNome());
		pessoa.setCpf(novaPessoa.getCpf());
		pessoa.setNascimento(novaPessoa.getNascimento());
		pessoa.setContato(novaPessoa.getContato());

		return repository.save(pessoa);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Pessoa findById(Long id) {
		Optional<Pessoa> pessoa = repository.findById(id);
		if (!pessoa.isPresent())
			return null;

		return pessoa.get();
	}

	public Page<Pessoa> findDinamic(Pessoa pessoa, Pageable pageable) {
		return repository.findAll(pessoa.toSpec(), pageable);
	}

}
