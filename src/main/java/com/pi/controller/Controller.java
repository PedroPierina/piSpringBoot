package com.pi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pi.placa.Placa;
import com.pi.repository.Repositorio;

@RestController
@RequestMapping("/database/placas")
public class Controller {
	@Autowired
	private Repositorio repo;
	
	@GetMapping
	public @ResponseBody Iterable<Placa> buscarTodas(){
		Iterable<Placa>  listaPlacas = repo.findAll();
		return listaPlacas;
		
	}
	
	@PostMapping
	public void addicionar(@RequestBody Placa placa) {
		repo.save(placa);
	}
	
	
	@GetMapping
	@RequestMapping("/database/placas/add")
	public String sendForm(Placa placa){
		return "AddPlaca";
	}
	
	 @PostMapping("/database/placas/add")
	    public String processForm(Placa Placa) {

	        return "showMessage";
	    }
}
