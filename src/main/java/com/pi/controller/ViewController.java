package com.pi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.pi.repository.Repositorio;

@Controller
public class ViewController {
	
	@Autowired
	private Repositorio repo;
	
	@RequestMapping("/import/placas")
	public String addicionar() {
		return "importPlacas";
	}
	
	@RequestMapping("/view/placas")
	public ModelAndView viewPlacas() {
		ModelAndView modelAndView = new ModelAndView("viewPlacas");
		modelAndView.addObject("placas", repo.findAll());
		return modelAndView;
	}

}
