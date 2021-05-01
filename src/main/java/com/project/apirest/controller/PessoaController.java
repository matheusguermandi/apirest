package com.project.apirest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.apirest.domain.Pessoa;
import com.project.apirest.domain.dto.PessoaDto;
import com.project.apirest.service.PessoaService;

@RestController
@RequestMapping("/api/v1/pessoa")
public class PessoaController {
	
	@Autowired
	private PessoaService service;
	
	@PostMapping
    public ResponseEntity<PessoaDto> create(@Valid @RequestBody Pessoa pessoa) throws Exception {
		PessoaDto novaPessoa = service.create(pessoa);
        return ResponseEntity.ok().body(novaPessoa);
    }

}
