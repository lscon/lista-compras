package com.lscon.listacompras.controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lscon.listacompras.model.Produto;
import com.lscon.listacompras.repository.Produtos;

@RestController
@RequestMapping("/api/produtos")
public class ApiProdutoController {

	@Autowired
	private Produtos produtos;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Collection<Produto> listaProdutos() {
		return produtos.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional<Produto> getProduto(@PathVariable("id") Long id) {
		return this.produtos.findById(id);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> listar() {
		return new ResponseEntity<List<Produto>>(new ArrayList<Produto>(produtos.findAll()), 
				HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeProduto(@PathVariable("id") Long id) {
		Optional<Produto> c = produtos.findById(id);
		if (c == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		produtos.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public  ResponseEntity<?> saveProduto(@RequestBody Produto produto) {
		return new ResponseEntity<Produto> (produtos.save(produto), HttpStatus.OK);
	}

}

