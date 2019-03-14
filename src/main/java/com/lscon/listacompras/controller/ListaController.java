package com.lscon.listacompras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.lscon.listacompras.model.Lista;
import com.lscon.listacompras.repository.Listas;

@Controller
@RequestMapping("/listas")
public class ListaController {

	@Autowired
	Listas listas;

	@RequestMapping("")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("ListaListagem.html");
		mv.addObject(new Lista());
		mv.addObject("listas",listas.findAll());
		return mv;
	}

	@RequestMapping(value="",method=RequestMethod.POST)
	public String gravar(Lista f) {
		listas.save(f);
		return "redirect:/listas";
	}

	@RequestMapping(value="alterar/{id}")
	public ModelAndView alterar(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("ListaListagem.html");
		Lista lista = listas.getOne(id);
		mv.addObject(lista);
		mv.addObject("listas",listas.findAll());
		return mv;
	}

	//Excluir
	@RequestMapping(value="/excluir/{id}")
	public String excluir(@PathVariable Long id) {
		listas.deleteById(id);
		//attributes.addFlashAttribute("mensagem", "Proprietario excluído com sucesso!");
		return "redirect:/listas";
	}

}