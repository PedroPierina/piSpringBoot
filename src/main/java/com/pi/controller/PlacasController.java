package com.pi.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.pi.placa.Placa;
import com.pi.placa.PlacaDetectada;
import com.pi.repository.Repositorio;
import com.pi.repository.RepositorioDetectada;

@RestController
@RequestMapping("/database/placas")
public class PlacasController {
	@Autowired
	private Repositorio repo;
	@Autowired
	private RepositorioDetectada repoDetec;
	
	@GetMapping	
	public @ResponseBody Iterable<Placa> buscarTodas(){
		Iterable<Placa>  listaPlacas = repo.findAll();
		return listaPlacas;	
	}
	
	@PostMapping
	public void addicionar(@RequestBody Placa placa) {
		repo.save(placa);
	}
	
	@PostMapping(value = "/import/csv", consumes = "multipart/form-data")
	public void importCSV(@RequestParam("import_file") MultipartFile file, HttpServletResponse httpResponse) throws IOException {
	   CsvMapper mapper = new CsvMapper();
	   CsvSchema schema = mapper.schemaFor(Placa.class).withHeader().withColumnReordering(true);
	   ObjectReader reader = mapper.readerFor(Placa.class).with(schema);
	   repo.saveAll(reader.<Placa>readValues(file.getInputStream()).readAll());
	   httpResponse.sendRedirect("/view/placas");
	}
	
	@PostMapping(value = "/detectada")
	public void detectada(@RequestBody PlacaDetectada placa) {
		repoDetec.save(placa);
	}
	
}
