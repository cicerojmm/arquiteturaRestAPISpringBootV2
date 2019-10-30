package br.com.example.spring.service;

import br.com.example.spring.dto.ProdutoDTO;
import br.com.example.spring.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IProdutoService {

    Page<Produto> listar(Pageable paginacao);

    Produto salvar(Produto produto);

    Optional<Produto> consultar(String codigo);

    Produto atualizar(Long id, ProdutoDTO produtoDTO);

    void deletar(Produto produto);
}
