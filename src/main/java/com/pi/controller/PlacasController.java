package com.pi.controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/database/placas")
public class PlacasController {
	@Autowired
	private Repositorio repo;
	@Autowired
	private RepositorioDetectada repoDetec;
	int countImage = 0;
	
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
	
	@PostMapping(value="/uploadImage")
    public @ResponseBody String uploadImage(@RequestParam("imageValue") String imageValue,HttpServletRequest request)
    {
        try
        {
            //This will decode the String which is encoded by using Base64 class
            byte[] imageByte = Base64.getDecoder().decode(imageValue);
            
            Path path = Paths.get("/piSpringBoot/src/main/resources/images" + countImage);
            
            Files.write(path, imageByte);
            
            return "success ";
        }
        catch(Exception e)
        {
            return "error = "+e;
        }

    }
}
