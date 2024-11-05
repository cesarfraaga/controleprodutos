package com.cesarfraga.controller;

import com.cesarfraga.entity.Produto;
import com.cesarfraga.exception.ProductPriceException;
import com.cesarfraga.request.IdRequest;
import com.cesarfraga.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping(value = "/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto) throws ProductPriceException {
        produto = service.save(produto);
        return ResponseEntity.ok().body(produto);
    }

    @PostMapping(value = "/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Produto> atualizarProduto(@RequestBody Produto produto) {
        Produto atualizado = service.update(produto);
        return ResponseEntity.ok().body(atualizado);
    }

    @PostMapping(value = "/deleteById")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Void> deletarProduto(@RequestBody IdRequest idRequest) {
        Long id = idRequest.getId();
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        Produto produto = service.findById(id);

        if (produto != null) {
            return ResponseEntity.ok().body(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping(value = "/findAll")
    public ResponseEntity<List<Produto>> buscarTodosOsProdutos() {
        List<Produto> produtos = service.findAll();
        return ResponseEntity.ok().body(produtos);
    }
}
