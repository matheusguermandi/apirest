package com.project.apirest.domain.dto;

import com.project.apirest.domain.Pessoa;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class PessoaDto {

	public Pessoa pessoa;
}
