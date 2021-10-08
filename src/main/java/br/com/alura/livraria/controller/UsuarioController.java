package br.com.alura.livraria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.livraria.dto.UsuarioDto;
import br.com.alura.livraria.dto.UsuarioFormDto;
import br.com.alura.livraria.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping
	public Page<UsuarioDto> listar(@PageableDefault(size = 10) org.springframework.data.domain.Pageable paginacao) {
		return service.listar(paginacao);
	}

	@PostMapping
	public void cadastrar(@RequestBody @Valid UsuarioFormDto dto) {
		service.cadastrar(dto);
	}
}
