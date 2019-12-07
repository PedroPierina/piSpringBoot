package com.pi.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pi.image.Image;
import com.pi.repository.FileRepository;

@Controller
public class SocketController {
	@RequestMapping(value="/image2", method=RequestMethod.POST)
	@MessageMapping("/image")
    @SendTo("/topic/image")
    public String sendMessage(@RequestBody String imageValue) {
		System.out.println("Entrei socketController");
            return "data:image/png;base64," + imageValue;
		
       
    }
}
