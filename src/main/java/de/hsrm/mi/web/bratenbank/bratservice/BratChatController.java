package de.hsrm.mi.web.bratenbank.bratservice;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class BratChatController {
    @MessageMapping("/topic/bratchat/toserver")
    @SendTo("/topic/bratchat/fromserver")
    public String back(@Valid @Payload String b){
        String neu = LocalDateTime.now() + " " + b;
        return neu; 
    }
    
}