package br.com.alura.livraria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.alura.livraria.dto.UsuarioDto;
import br.com.alura.livraria.dto.UsuarioFormDto;
import br.com.alura.livraria.modelo.Usuario;

@Service
public class UsuarioService {

	private List<Usuario> usuarios = new ArrayList<>();
	private ModelMapper modelMapper = new ModelMapper();
	
	public List<UsuarioDto> listar(){
		return usuarios
				.stream().map(u -> modelMapper
				.map(u, UsuarioDto.class))
				.collect(Collectors.toList());
	}
	
	public void cadastrar(UsuarioFormDto dto) {
		Usuario usuario = modelMapper.map(dto, Usuario.class);
		
		String senha = new Random().nextInt(999999) + "";
		usuario.setSenha(senha);
		
		usuarios.add(usuario);
	}	
}
