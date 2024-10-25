package com.cesarfraga.controller;

import com.cesarfraga.entity.Produto;
import com.cesarfraga.exception.ProductPriceException;
import com.cesarfraga.request.IdRequest;
import com.cesarfraga.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

    @Autowired //
    private ProdutoService service;

    @PostMapping(value = "/save")
    public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto) throws ProductPriceException {
        produto = service.save(produto);
        return ResponseEntity.ok().body(produto);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Produto> atualizarProduto(@RequestBody Produto produto) {
        Produto atualizado = service.update(produto);
        return ResponseEntity.ok().body(atualizado);
    }

    @PostMapping(value = "/deleteById")
    public ResponseEntity<Produto> deletarProduto(@RequestBody IdRequest idRequest) {
        Long id = idRequest.getId();
        service.deleteById(id);
        return null;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        //Long id = idRequest.getId();
        Produto produto = service.findById(id);

        if (produto != null) {
            return ResponseEntity.ok().body(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/findAll")
    public ResponseEntity<List<Produto>> buscarTodosOsProdutos() {
        List<Produto> produtos = service.findAll();
        return ResponseEntity.ok().body(produtos);
    }

}
