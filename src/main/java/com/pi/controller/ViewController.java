package com.pi.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pi.image.Image;
import com.pi.repository.FileRepository;
import com.pi.repository.Repositorio;
import com.pi.repository.RepositorioDetectada;

@Controller
public class ViewController {
	
	@Autowired
	private Repositorio repo;
	@Autowired
	private RepositorioDetectada repoDetec;
	@Autowired
	private FileRepository fileRepository;
	
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
	
	@RequestMapping("/view/placasDetectadas")
	public ModelAndView viewPlacasDetectadas() {
		ModelAndView modelAndView = new ModelAndView("viewPlacas");
		modelAndView.addObject("placas", repoDetec.findAll());
		return modelAndView;
	}
	
	@RequestMapping("/image/{image_id}")
	public ModelAndView getImage(@PathVariable("image_id") String image_id) {
		ModelAndView modelAndView = new ModelAndView("viewImage");
		modelAndView.addObject("image", image_id);
		return modelAndView;
	}
	
	@RequestMapping("/view/image/{image_id}")
	public void viewImagePage(@PathVariable("image_id") String image_id, HttpServletResponse response) throws IOException {
		System.out.println("Sodexo");
		response.setContentType("image/jpeg");
		ServletOutputStream outputStream = response.getOutputStream();
//		List<Image> images = fileRepository.findAll();
//		Image image = images.get(0);
		System.out.println(image_id + "guilhere");
		Optional<Image> img = fileRepository.findById(Integer.parseInt(image_id));
		Image image = img.get();
		
		outputStream.write(image.getData());
		outputStream.close();
		
	}
}
