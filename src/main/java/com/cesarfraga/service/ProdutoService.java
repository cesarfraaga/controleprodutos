package com.cesarfraga.service;

import com.cesarfraga.entity.Produto;
import com.cesarfraga.exception.ProductNullException;
import com.cesarfraga.exception.ProductPriceException;
import com.cesarfraga.repository.ProdutoRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto save(Produto produto) throws ProductPriceException {
        if (produto.getNome() == null || produto.getPreco() == null)
            throw new ProductNullException();
        if (produto.getPreco() < 0)
            throw new ProductPriceException();
        return repository.save(produto);
    }

    public Produto update(Produto produto) {
        if (repository.existsById(produto.getId())) {
            return repository.save(produto);
        } else {
            throw new ResourceNotFoundException("Produto com ID " + produto.getId() + " não existe.");
        }
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Produto findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Produto> findAll() {
        return repository.findAllByOrderByIdAsc();
    }
}
