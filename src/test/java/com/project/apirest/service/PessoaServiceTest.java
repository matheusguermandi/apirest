package com.project.apirest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.apirest.domain.Contato;
import com.project.apirest.domain.Pessoa;
import com.project.apirest.repository.PessoaRepository;

public class PessoaServiceTest {
	private Validator validator;

	@Mock
	private PessoaRepository repository;

	@InjectMocks
	private PessoaService service;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	private static final Long ID = (long) 33;
	private static final Long ID_NOT_EXISTENT = (long) 1;

	@Test
	void should_be_able_to_find_by_id() {
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

	@Test
	void should_be_able_to_create_pessoa() throws Exception {
		Pessoa pessoa = mock(Pessoa.class);
		Contato contato = mock(Contato.class);
		LocalDate nascimento = LocalDate.now().minusDays(365);

		when(pessoa.getNome()).thenReturn("Matheus");
		when(pessoa.getCpf()).thenReturn("29443658003");
		when(pessoa.getNascimento()).thenReturn(nascimento);
		when(pessoa.getContato()).thenReturn(Arrays.asList(contato));

		service.create(pessoa);
		verify(repository).save(pessoa);
	}

	@Test
	void should_be_not_able_to_create_pessoa_with_invalid_cpf() throws Exception {
		Pessoa pessoa = mock(Pessoa.class);
		Contato contato = mock(Contato.class);
		LocalDate nascimento = LocalDate.now();

		when(pessoa.getNome()).thenReturn("Matheus");
		when(pessoa.getCpf()).thenReturn("12345678900");
		when(pessoa.getNascimento()).thenReturn(nascimento);
		when(pessoa.getContato()).thenReturn(Arrays.asList(contato));

		Set<ConstraintViolation<Pessoa>> violations = validator.validate(pessoa);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	void should_be_not_able_to_create_pessoa_with_invalid_date() throws Exception {
		Pessoa pessoa = mock(Pessoa.class);
		Contato contato = mock(Contato.class);
		LocalDate nascimento = LocalDate.now().plusDays(10);

		when(pessoa.getNome()).thenReturn("Matheus");
		when(pessoa.getCpf()).thenReturn("29443658003");
		when(pessoa.getNascimento()).thenReturn(nascimento);
		when(pessoa.getContato()).thenReturn(Arrays.asList(contato));

		Set<ConstraintViolation<Pessoa>> violations = validator.validate(pessoa);
		assertFalse(violations.isEmpty());
	}

	@Test
	void should_be_not_able_to_create_pessoa_with_invalid_contato() throws Exception {
		Pessoa pessoa = mock(Pessoa.class);
		LocalDate nascimento = LocalDate.now().plusDays(10);

		when(pessoa.getNome()).thenReturn("Matheus");
		when(pessoa.getCpf()).thenReturn("29443658003");
		when(pessoa.getNascimento()).thenReturn(nascimento);

		Set<ConstraintViolation<Pessoa>> violations = validator.validate(pessoa);
		assertFalse(violations.isEmpty());
	}

	@Test
    void should_be_able_to_update_pessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Matheus");
        pessoa.setCpf("29443658003");
        
        Pessoa novaPessoa = new Pessoa();
        novaPessoa.setNome("Matheus 01");
        novaPessoa.setCpf("58441349070");
        
        when(repository.findById(ID)).thenReturn(Optional.of(pessoa));
        when(repository.save(pessoa)).thenReturn(novaPessoa);
        
        Pessoa update = service.update(novaPessoa, ID);
        
        assertEquals("Matheus 01", update.getNome());
        assertEquals("58441349070", update.getCpf());

    }

    @Test
    void should_be_able_to_delete_pessoa() {
        service.delete(ID);
        verify(repository).deleteById(ID);
    }
}