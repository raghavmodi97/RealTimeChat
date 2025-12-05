package com.realtimechat.realtimechat.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.realtimechat.realtimechat.dto.MessageDTO;
import com.realtimechat.realtimechat.service.ChatService;
import com.realtimechat.realtimechat.model.Message;
import com.realtimechat.realtimechat.dto.TypingDTO;
@Controller
public class ChatController {
    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    public ChatController(ChatService chatService,SimpMessagingTemplate messagingTemplate){
        this.chatService=chatService;
        this.messagingTemplate=messagingTemplate;
    }

    @MessageMapping("/chat/{conversationId}")
    public void handleMessage(
            @Valid @Payload MessageDTO incomingMessage,
        @DestinationVariable Long conversationId
    ) {

        Message saved=this.chatService.saveMessage(incomingMessage);
        MessageDTO dto=this.chatService.toDTO(saved, "John");
        messagingTemplate.convertAndSend(
            "/topic/conversations." + conversationId,
            dto
        );
    }

    @MessageMapping("/typing/{conversationId}")
    public void handleTyping(
            @Payload TypingDTO typingEvent,
            @DestinationVariable Long conversationId
    ){
        messagingTemplate.convertAndSend(
                "/topic/typing." + conversationId,
                typingEvent
        );
    }
}
