package br.com.alura.livraria.service;

import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.alura.livraria.dto.UsuarioDto;
import br.com.alura.livraria.dto.UsuarioFormDto;
import br.com.alura.livraria.modelo.Usuario;
import br.com.alura.livraria.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	private ModelMapper modelMapper = new ModelMapper();

	public Page<UsuarioDto> listar(org.springframework.data.domain.Pageable paginacao) {
		Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
		return usuarios.map(u -> modelMapper.map(u, UsuarioDto.class));
	}

	public void cadastrar(UsuarioFormDto dto) {
		Usuario usuario = modelMapper.map(dto, Usuario.class);

		String senha = new Random().nextInt(999999) + "";
		usuario.setSenha(senha);

		usuarioRepository.save(usuario);
	}
}
