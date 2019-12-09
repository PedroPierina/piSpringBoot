package com.pi.webSocket;

import org.json.JSONObject;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.pi.placa.PlacaDetectada;

public class CommService {

	  private static SimpMessagingTemplate template;

	  public static void setTemplate(SimpMessagingTemplate tmplt) {
	    template = tmplt;
	  }

	  public static void send(PlacaDetectada placa) {
	    template.convertAndSend("/topic/image", placa);
	  }
	}
