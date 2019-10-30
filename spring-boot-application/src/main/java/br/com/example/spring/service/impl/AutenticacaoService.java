package br.com.example.spring.service.impl;

import br.com.example.spring.constante.MensagemValidacao;
import br.com.example.spring.model.Usuario;
import br.com.example.spring.service.IUsuarioService;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private IUsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String email) {
		Usuario usuario = usuarioService.consultar(email);
		List<GrantedAuthority> listaAutorizacoes = new ArrayList<>();

		if (!validarUsuario(usuario)) {
			throw new UsernameNotFoundException(MensagemValidacao.ERRO_USUARIO_SEM_PERMISSAO);
		}

		return usuario;
	}

	private boolean validarUsuario(Usuario usuario) {
		boolean usuarioValidado = false;

		if (usuario != null && usuario.getPermissao() != null && BooleanUtils.toBoolean(usuario.getStatus())) {
			usuarioValidado = true;
		}

		return usuarioValidado;
	}

}
