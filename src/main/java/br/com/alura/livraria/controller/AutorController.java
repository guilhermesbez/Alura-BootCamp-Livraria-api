package br.com.alura.livraria.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.livraria.dto.AutorDto;
import br.com.alura.livraria.dto.AutorFormDto;
import br.com.alura.livraria.service.AutorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/autor")
@Api(tags = "Autor")
public class AutorController {

	@Autowired
	private AutorService service;
	
	@GetMapping
	@ApiOperation("Listar Autor")
	public Page<AutorDto> listar(org.springframework.data.domain.Pageable paginacao) {
		return service.listar(paginacao);
	}

	@PostMapping
	@ApiOperation("Cadastrar Autor")
	public ResponseEntity<AutorDto> cadastrar(@RequestBody @Valid AutorFormDto dto, UriComponentsBuilder uriBuilder) {
		AutorDto cadastrado = service.cadastrar(dto);
		URI endereco = uriBuilder.path("/autor/{id}").buildAndExpand(cadastrado.getId()).toUri();
		
		return ResponseEntity.created(endereco).body(cadastrado);
	}
}
