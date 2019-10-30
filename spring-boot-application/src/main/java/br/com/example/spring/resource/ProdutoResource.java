package br.com.example.spring.resource;

import br.com.example.spring.conversor.ConversorProduto;
import br.com.example.spring.dto.ProdutoDTO;
import br.com.example.spring.model.Produto;
import br.com.example.spring.service.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource extends BaseResource<ProdutoDTO> {

    @Autowired
    IProdutoService produtoService;

    @Autowired
    ConversorProduto conversorProduto;

    @GetMapping
    @Cacheable(value = "listaDeProdutos")
    public Page<ProdutoDTO> listar(@PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<Produto> produtos = produtoService.listar(paginacao);
        return conversorProduto.converterPageEntidadeParaDto(produtos);
    }

    @PostMapping
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<ProdutoDTO> cadastrar(@RequestBody @Valid ProdutoDTO produtoDTO, UriComponentsBuilder uriBuilder) {
        Produto produto = conversorProduto.converterDtoParaEntidade(produtoDTO);
        produtoService.salvar(produto);
        String path = "/produtos/{id}";
        return responderItemCriadoComURI(produtoDTO, uriBuilder, path, produtoDTO.getCodigo());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ProdutoDTO> detalhar(@PathVariable String codigo) {
        Optional<Produto> produto = produtoService.consultar(codigo);
        if (produto.isPresent()) {
            return responderSucessoComItem(conversorProduto.converterEntidadeParaDto(produto.get()));
        }
        return responderItemNaoEncontrado();
    }

    @PutMapping("/{codigo}")
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable String codigo, @RequestBody @Valid ProdutoDTO produtoDTO) {
        Optional<Produto> optionalProduto = produtoService.consultar(codigo);
        if (optionalProduto.isPresent()) {
            Produto produto = produtoService.atualizar(optionalProduto.get().getId(), produtoDTO);
            return responderSucessoComItem(conversorProduto.converterEntidadeParaDto(produto));
        }

        return responderItemNaoEncontrado();
    }

    @DeleteMapping("/{codigo}")
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable String codigo) {
        Optional<Produto> optionalProduto = produtoService.consultar(codigo);
        if (optionalProduto.isPresent()) {
            produtoService.deletar(optionalProduto.get());
            return responderSucesso();
        }

        return responderItemNaoEncontrado();
    }

}