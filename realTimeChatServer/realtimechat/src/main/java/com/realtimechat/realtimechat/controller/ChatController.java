package com.realtimechat.realtimechat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.realtimechat.realtimechat.dto.MessageDTO;
import com.realtimechat.realtimechat.service.ChatService;
import com.realtimechat.realtimechat.model.Message;

@Controller
public class ChatController {
    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(ChatService chatService,SimpMessagingTemplate messagingTemplate){
        this.chatService=chatService;
        this.messagingTemplate=messagingTemplate;
    }

    @MessageMapping("/chat/{conversationId}")
    public void handleMessage(
        @Payload MessageDTO incomingMessage,
        @DestinationVariable Long conversationId
    ) {
        Message saved=this.chatService.saveMessage(incomingMessage);
        MessageDTO dto=this.chatService.toDTO(saved, "John");
        messagingTemplate.convertAndSend(
            "/topic/conversations." + conversationId, 
            dto
        );

    }
    
}
