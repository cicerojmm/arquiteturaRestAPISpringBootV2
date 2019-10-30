package br.com.example.spring.service.impl;

import br.com.example.spring.dto.ProdutoDTO;
import br.com.example.spring.model.Produto;
import br.com.example.spring.repository.IProdutoRepository;
import br.com.example.spring.service.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService implements IProdutoService {

    @Autowired
    IProdutoRepository produtoRepository;

    @Override
    public Page<Produto> listar(Pageable paginacao) {
        return produtoRepository.findAll(paginacao);
    }

    @Override
    public Produto salvar(Produto produto) {
       return produtoRepository.save(produto);
    }

    @Override
    public Optional<Produto> consultar(String codigo) {
        return produtoRepository.findByCodigo(codigo);
    }

    @Override
    public Produto atualizar(Long id, ProdutoDTO produtoDTO) {
        Produto produto = produtoRepository.getOne(id);
        produto.setNome(produtoDTO.getNome());
        produto.setStatus(produtoDTO.getStatus());

        return produto;
    }

    @Override
    public void deletar(Produto produto) {
        produtoRepository.delete(produto);
    }
}