package com.project.apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.apirest.domain.Pessoa;
import com.project.apirest.domain.dto.PessoaDto;
import com.project.apirest.repository.PessoaRepository;

import java.util.Optional;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	public PessoaDto create(Pessoa pessoa) {
		PessoaDto pessoaDto = new PessoaDto();
		pessoaDto.setPessoa(repository.save(pessoa));
		return pessoaDto;
	}

	public Pessoa getPessoaById(Long id) {
		Optional<Pessoa> pessoa = repository.findById(id);
		if (!pessoa.isPresent())
			return null;
		
		return pessoa.get();
	}

	public Pessoa update(Pessoa novaPessoa, Long id) {
		Pessoa pessoa = getPessoaById(id);

		pessoa.setNome(novaPessoa.getNome());
		pessoa.setCpf(novaPessoa.getCpf());
		pessoa.setNascimento(novaPessoa.getNascimento());
		pessoa.setContato(novaPessoa.getContato());

		return repository.save(pessoa);

	}

}
