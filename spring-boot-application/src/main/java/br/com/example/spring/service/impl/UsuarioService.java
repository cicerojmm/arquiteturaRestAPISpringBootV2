package br.com.example.spring.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.example.spring.constante.MensagemValidacao;
import br.com.example.spring.conversor.ConversorUsuario;
import br.com.example.spring.dto.UsuarioDTO;
import br.com.example.spring.exception.NegocioException;
import br.com.example.spring.model.Usuario;
import br.com.example.spring.repository.IUsuarioRepository;
import br.com.example.spring.service.IUsuarioService;
import br.com.example.spring.util.LogUtil;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	IUsuarioRepository usuarioRepository;
	@Autowired
	ConversorUsuario conversorUsuario;

	@Override
	public UsuarioDTO consultarUsuario(String email) {
		return conversorUsuario.converterEntidadeParaDto(consultar(email));
	}

	@Override
	public  Usuario consultar(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
	@Override
	public Usuario validar(String email) {
		Usuario usuario = consultar(email);
		if (usuario == null) {
			throw new NegocioException(MensagemValidacao.ERRO_USUARIO_INVALIDO);
		}

		return usuario;
	}

	@Override
	public UsuarioDTO criar(UsuarioDTO usuarioDTO) {
		if (consultar(usuarioDTO.getEmail()) == null) {
			usuarioDTO.setSenha(gerarSenhaAleatoria());

			Usuario usuario = conversorUsuario.converterDtoParaEntidade(usuarioDTO);
			usuario.setSenha(gerarSenhaCriptografada(usuarioDTO.getSenha()));
			usuarioRepository.save(usuario);
		} else {
			throw new NegocioException(MensagemValidacao.ERRO_USUARIO_EXISTENTE);
		}

		return usuarioDTO;
	}

	@Override
	@Transactional
	public UsuarioDTO atualizar(UsuarioDTO usuarioDTO) {
		Usuario usuario = consultar(usuarioDTO.getEmail());
		String senha;
		if (usuario != null) {
			senha = gerarSenhaAleatoria();
			usuario.setSenha(senha);
			usuario.setStatus(usuarioDTO.getStatus());
			usuarioRepository.atualizarSenha(gerarSenhaCriptografada(senha), usuarioDTO.getEmail());
		} else {
			throw new NegocioException(MensagemValidacao.ERRO_USUARIO_INVALIDO);
		}

		UsuarioDTO usuarioRetorno = conversorUsuario.converterEntidadeParaDto(usuario);
		return usuarioRetorno;
	}

	private String gerarSenhaAleatoria() {
		SecretKey secretKey;
		try {
			SecureRandom random = new SecureRandom();
			KeyGenerator generator = KeyGenerator.getInstance("AES");
			int keyBitSize = 256;
			generator.init(keyBitSize, random);
			secretKey = generator.generateKey();
		} catch (NoSuchAlgorithmException e) {
			LogUtil.error(e);
			throw new NegocioException(MensagemValidacao.ERRO_USUARIO_INVALIDO);
		}

		return Base64.getEncoder().encodeToString(secretKey.getEncoded());
	}

	private String gerarSenhaCriptografada(String senha) {
		return new BCryptPasswordEncoder().encode(senha);
	}
}
