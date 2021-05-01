package com.project.apirest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.apirest.domain.Pessoa;
import com.project.apirest.domain.dto.PessoaDto;
import com.project.apirest.service.PessoaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/pessoa")
@CrossOrigin(origins = "*")
@Api(value="API REST - Pessoas")
public class PessoaController {

	@Autowired
	private PessoaService service;

	@ApiOperation(value="Cadastra uma pessoa")
	@PostMapping
	public ResponseEntity<PessoaDto> create(@Valid @RequestBody Pessoa pessoa) throws Exception {
		PessoaDto novaPessoa = service.create(pessoa);
		return ResponseEntity.ok().body(novaPessoa);
	}

	@ApiOperation(value="Atualiza uma pessoa")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Pessoa pessoa, @PathVariable Long id) {
		service.update(pessoa, id);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value="Deleta uma pessoa")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value="Retorna uma única pessoa")
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> findById(@PathVariable("id") Long id) {
		Pessoa findById = service.findById(id);
		return ResponseEntity.ok().body(findById);
	}

	@ApiOperation(value="Retorna uma lista de pessoas, com filtro e paginação")
	@GetMapping("/find")
	public ResponseEntity<Page<Pessoa>> findDinamic(Pessoa pessoa, Pageable pageable) {
		Page<Pessoa> findDinamic = service.findDinamic(pessoa, pageable);
		return ResponseEntity.ok().body(findDinamic);
	}
	
	@ApiOperation(value="Retorna todas as pessoas cadastradas")
	@GetMapping("/all")
	public ResponseEntity<List<Pessoa>> findAll() {
		List<Pessoa> findAll = service.findAll();
		return ResponseEntity.ok().body(findAll);
	}

}
