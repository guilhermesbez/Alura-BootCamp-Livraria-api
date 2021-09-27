package br.com.alura.livraria.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioFormDto {
	
	@NotBlank
	@Size(min = 1, max = 140)
	private String nome;
	@NotBlank
	@Size(min = 1, max = 140)
	private String login;
	@NotBlank
	@Size(min = 1, max = 8)
	private String senha;
}
