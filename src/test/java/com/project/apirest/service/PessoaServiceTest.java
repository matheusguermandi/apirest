package com.project.apirest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.apirest.domain.Pessoa;
import com.project.apirest.repository.PessoaRepository;

public class PessoaServiceTest {
	@Mock
	private PessoaRepository repository;

	@InjectMocks
	private PessoaService service;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	private static final Long ID = (long) 33;
	private static final Long ID_NOT_EXISTENT = (long) 1;

	@Test
	void should_be_able_find_by_id() {
		Pessoa pessoaMock = mock(Pessoa.class);
		when(repository.findById(ID)).thenReturn(Optional.of(pessoaMock));

		Pessoa pessoa = service.findById(ID);

		assertEquals(pessoaMock, pessoa);
	}

	@Test
	void should_be_not_able_to_find_by_id_not_existent() {
		when(repository.findById(ID_NOT_EXISTENT)).thenReturn(Optional.ofNullable(null));
		Pessoa pessoa = service.findById(ID_NOT_EXISTENT);
		
		assertNull(pessoa);
	}
	
	

}