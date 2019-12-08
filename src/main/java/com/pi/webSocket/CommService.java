package com.pi.webSocket;

import org.json.JSONObject;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class CommService {

	  private static SimpMessagingTemplate template;

	  public static void setTemplate(SimpMessagingTemplate tmplt) {
	    template = tmplt;
	  }

	  public static void send(JSONObject jo) {
	    template.convertAndSend("/topic/image", jo);
	  }
	}
