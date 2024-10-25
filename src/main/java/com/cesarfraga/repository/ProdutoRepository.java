package com.cesarfraga.repository;

import com.cesarfraga.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository {
    List<Produto> findAllByOrderByIdAsc();
}
