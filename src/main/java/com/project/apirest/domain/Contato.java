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

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(required = true, example = "")
	private Long id;

	@NotBlank
	@Column(nullable = false)
	@ApiModelProperty(required = true, example = "Edson")
	private String nome;

	@NotBlank
	@Column(nullable = false)
	@ApiModelProperty(required = true, example = "11 99999-9999")
	private String telefone;

	@NotBlank
	@Column(nullable = false)
	@Email(message = "Ooops ... este e-mail não é valido")
	@ApiModelProperty(required = true, example = "edson@hotmail.com")
	private String email;
	
	@ManyToOne
	@JsonBackReference
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = false)
	@ApiModelProperty(required = true)
    private Pessoa pessoa;

}
