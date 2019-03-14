package com.lscon.listacompras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lscon.listacompras.model.Produto;
import com.lscon.listacompras.repository.Listas;
import com.lscon.listacompras.repository.Produtos;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	
	@Autowired
	Produtos produtos;
	
	@Autowired 
	Listas listas;
	
	@RequestMapping("")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("ProdutosListagem.html");
		mv.addObject(new Produto());
		mv.addObject("listas",listas.findAll());
		mv.addObject("produtos",produtos.findAll());
		return mv;
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public String gravar(Produto c) {
		produtos.save(c);
		return "redirect:/produtos";
	}
	
	@RequestMapping(value="alterar/{id}")
	public ModelAndView alterar(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("ProdutosListagem.html");
		Produto produto = produtos.getOne(id);
		mv.addObject(produto);
		mv.addObject("listas",listas.findAll());
		mv.addObject("produtos",produtos.findAll());
		return mv;
	}
	
	//Excluir
	@RequestMapping(value="/excluir/{id}")
	public String excluir(@PathVariable Long id) {
		produtos.deleteById(id);
		return "redirect:/produtos";
	}
	
}
