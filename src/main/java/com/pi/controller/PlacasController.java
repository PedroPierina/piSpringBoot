package com.pi.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
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
import com.pi.image.Image;
import com.pi.placa.Placa;
import com.pi.placa.PlacaDetectada;
import com.pi.repository.FileRepository;
import com.pi.repository.Repositorio;
import com.pi.repository.RepositorioDetectada;
import com.pi.webSocket.CommService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;

@RestController
@RequestMapping("/database/placas")
public class PlacasController {
	@Autowired
	private Repositorio repo;
	@Autowired
	private RepositorioDetectada repoDetec;
	@Autowired
	private FileRepository fileRepository;
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
	
	@PostMapping(value = "/uploadImage")
	public void detectada(@RequestBody PlacaDetectada placa) {
		repoDetec.save(placa);
	}
	
	
	@PostMapping(value="/detectada")
    public @ResponseBody String uploadImage(@RequestBody PlacaDetectada placa)
    {
        try
        {
        	System.out.println(placa);
        	System.out.println();
        	System.out.println(placa.getBase64Image());
        	System.out.println();
        	
        	String auxS = "data:image/png;base64," + placa.getBase64Image().toString();
        	byte[] aux = auxS.getBytes();
        	
//        	placa.setBase64Image(aux);
        	
            CommService.send(placa);
            
            repoDetec.save(placa);
            return "success ";
        }
        catch(Exception e)
        {
            return "error = "+e;
        }

    }
}
