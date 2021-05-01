package com.project.apirest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
public class Contato  {
	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String nome;

	@NotBlank
	@Column(nullable = false)
	private String telefone;

	@NotBlank
	@Email(message = "Ooops ... esté e-mail não é valido")
	@Column(nullable = false)
	private String email;
	
	@ManyToOne
	@JsonBackReference
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = false)
    private Pessoa pessoa;

}
