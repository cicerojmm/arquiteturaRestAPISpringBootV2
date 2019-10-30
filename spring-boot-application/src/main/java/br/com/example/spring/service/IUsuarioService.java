package br.com.example.spring.service;

import br.com.example.spring.dto.UsuarioDTO;
import br.com.example.spring.model.Usuario;

public interface IUsuarioService {

	UsuarioDTO consultarUsuario(String email);
	
	Usuario consultar(String email);
	
	Usuario validar(String email) ;

	UsuarioDTO criar(UsuarioDTO usuarioDTO);
	
	UsuarioDTO atualizar(UsuarioDTO usuarioDTO);

}
