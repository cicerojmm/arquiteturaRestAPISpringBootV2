package br.com.example.spring.conversor;

import br.com.example.spring.dto.ProdutoDTO;
import br.com.example.spring.model.Produto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ConversorProduto extends ConversorBase<Produto, ProdutoDTO>  {
    @Override
    public ProdutoDTO converterEntidadeParaDto(Produto entidade) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Produto, ProdutoDTO>() {
            @Override
            protected void configure() {
            }
        });
        return modelMapper.map(entidade, ProdutoDTO.class);
    }

    @Override
    public Produto converterDtoParaEntidade(ProdutoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<ProdutoDTO, Produto>() {
            @Override
            protected void configure() {
            }
        });
        return modelMapper.map(dto, Produto.class);
    }


    public Page<ProdutoDTO> converterPageEntidadeParaDto(Page<Produto> entidade) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Page<Produto>, Page<ProdutoDTO>>() {
            @Override
            protected void configure() {
            }
        });
        return modelMapper.map(entidade, Page.class);
    }
}
