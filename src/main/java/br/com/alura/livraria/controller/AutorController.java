package br.com.alura.livraria.controller;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.livraria.dto.AtualizacaoAutorFormDto;
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
	
	@PutMapping
	@ApiOperation("Atualizar Autor")
	public ResponseEntity<AutorDto> atualizar(@RequestBody @Valid AtualizacaoAutorFormDto dto) {
		AutorDto atualizada = service.atualizar(dto);
		
		return ResponseEntity.ok(atualizada);
	}
	
	@DeleteMapping("{id}")
	@ApiOperation("Exclui Autor")
	public ResponseEntity<AutorDto> remover(@PathVariable @NotNull Long id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Listar um unico Autor")
	public ResponseEntity<AutorDto> detalhar(@PathVariable @NotNull Long id) {
		AutorDto dto = service.detalhar(id);
		return ResponseEntity.ok(dto);
	}
}
