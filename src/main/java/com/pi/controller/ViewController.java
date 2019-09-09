package com.pi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pi.placa.Placa;

@Controller
@RequestMapping("/import")
public class ViewController {
	
	@RequestMapping("/placas")
	public String addicionar() {
		return "importPlacas";
	}

}
