package br.com.example.spring.resource;

import br.com.example.spring.dto.UsuarioDTO;
import br.com.example.spring.exception.RestAPIException;
import br.com.example.spring.service.IUsuarioService;
import br.com.example.spring.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/usuarios", produces = "application/json")
public class UsuarioResource extends BaseResource<UsuarioDTO>  {

	@Autowired
	private IUsuarioService usuarioService;

	@PostMapping(value = "")
	public ResponseEntity<UsuarioDTO> salvar(@Valid @RequestBody UsuarioDTO usuario) {
		try {
			UsuarioDTO usuarioRetorno = usuarioService.criar(usuario);
			return responderItemCriado(usuarioRetorno);
		} catch (Exception e) {
			LogUtil.error(e);
			throw new RestAPIException(e.getMessage());
		}
	}

	@PutMapping(value = "")
	public ResponseEntity<UsuarioDTO> atualizar(@Valid @RequestBody UsuarioDTO usuario) {
		try {
			UsuarioDTO usuarioRetorno = usuarioService.atualizar(usuario);
			return responderSucessoComItem(usuarioRetorno);
		} catch (Exception e) {
			LogUtil.error(e);
			throw new RestAPIException(e.getMessage());
		}
	}

	@GetMapping(value = "")
	public ResponseEntity<UsuarioDTO> consultar(@RequestParam(value = "email") String email) {
		try {
			UsuarioDTO usuario = usuarioService.consultarUsuario(email);
			return responderSucessoComItem(usuario);
		} catch (Exception e) {
			LogUtil.error(e);
			throw new RestAPIException(e.getMessage());
		}
	}
}
