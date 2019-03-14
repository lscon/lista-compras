package com.lscon.listacompras.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lscon.listacompras.model.Lista;
import com.lscon.listacompras.repository.Listas;

@RestController
@RequestMapping("/api/listas")
public class ApiListaController {
	
	@Autowired
	Listas listas;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Collection <Lista> allListas() {
		return listas.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional <Lista> oneLista(@PathVariable("id") Long id){
		return listas.findById(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeLista(@PathVariable("id") Long id) {
		Optional<Lista> f = listas.findById(id);
		if (f == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		listas.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public  ResponseEntity<?> saveLista(@RequestBody Lista lista) {
		return new ResponseEntity<Lista> (listas.save(lista), HttpStatus.OK);
	}

}
