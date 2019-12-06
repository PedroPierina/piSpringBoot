package com.pi.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.pi.image.Image;
import com.pi.repository.FileRepository;

@Controller
public class SocketController {
	@Autowired
	private FileRepository fileRepository;
	int countImage = 0;	
	@MessageMapping("/image")
    @SendTo("/topic/image")
    public String sendMessage(@Payload String imageValue) {
		try
        {
        	countImage++;
            //This will decode the String which is encoded by using Base64 class
            byte[] imageByte = Base64.getDecoder().decode(imageValue);
            System.out.println(imageByte.toString());
//            Path path = Paths.get("D:\\Workspace\\piSpringBoot\\src\\main\\resources\\images\\" + countImage+ ".png");
            
            
            Image imagem = new Image();
//            String blob = Arrays.toString(imageByte);
            
            imagem.setData(imageByte);
            imagem.setFileName("Imagem_" + countImage);
            imagem.setFileType("png");
            
            fileRepository.save(imagem);
            
            return "data:image/png;base64," + imageValue;
        }
        catch(Exception e)
        {
            return "error = "+e;
        }
		
       
    }
}
