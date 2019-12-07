package com.pi.webSocket;

import org.springframework.messaging.simp.SimpMessagingTemplate;

public class CommService {

	  private static SimpMessagingTemplate template;

	  public static void setTemplate(SimpMessagingTemplate tmplt) {
	    template = tmplt;
	  }

	  public static void send(String message) {
	    template.convertAndSend("/topic/image", message);
	  }
	}
