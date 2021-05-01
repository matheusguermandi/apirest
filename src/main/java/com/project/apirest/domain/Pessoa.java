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
	private Integer id;

	@NotBlank
	@Column(nullable = false)
	private String nome;

	@CPF(message = "CPF invalido")
	@NotBlank
	@Column(nullable = false)
	private String cpf;

	@Column(nullable = false)
	@NotBlank
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate nascimento;
	
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "pessoa")
    @JsonManagedReference
    private List<Contato> contato;
}
