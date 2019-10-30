package br.com.example.spring.conversor;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import br.com.example.spring.dto.UsuarioDTO;
import br.com.example.spring.model.Usuario;

@Component
public class ConversorUsuario extends ConversorBase<Usuario, UsuarioDTO> {

	@Override
	public UsuarioDTO converterEntidadeParaDto(Usuario entidade) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<Usuario, UsuarioDTO>() {
			@Override
			protected void configure() {
				map().setPermissao(source.getPermissao());
			}
		});
		return modelMapper.map(entidade, UsuarioDTO.class);
	}

	@Override
	public Usuario converterDtoParaEntidade(UsuarioDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<UsuarioDTO, Usuario>() {
			@Override
			protected void configure() {
				map().setPermisao(source.getPermissao());
			}
		});
		return modelMapper.map(dto, Usuario.class);
	}

}
