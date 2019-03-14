package com.lscon.listacompras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lscon.listacompras.model.Produto;

public interface  Produtos extends JpaRepository <Produto,Long> {

}
