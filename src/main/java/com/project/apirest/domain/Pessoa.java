package com.project.apirest.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String nome;

	@NotBlank
	@CPF(message = "Ooops ... este cpf não é valido")
	@Column(nullable = false)
	private String cpf;

	@NotNull
	@Past(message = "Ooops ... esta data não é valida")
	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate nascimento;
	
	@JsonManagedReference
	@NotEmpty(message = "Ooops ... é necessário possuir ao menos um contato")
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "pessoa")
    private List<Contato> contato;
}
