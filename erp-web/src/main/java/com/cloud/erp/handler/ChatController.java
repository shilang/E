package com.cloud.erp.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

	@Autowired
	private SimpMessagingTemplate template;
	
	@MessageMapping("/hello")
	public String send(String message){
		String text = "[" + System.currentTimeMillis() + "]:" + message;
		
		template.convertAndSend("/topic/greetings", text);
	
		return text;
	}
}
