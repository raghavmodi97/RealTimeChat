package com.realtimechat.realtimechat.service;

import org.springframework.stereotype.Service;
import com.realtimechat.realtimechat.repository.MessageRepository;
import com.realtimechat.realtimechat.dto.MessageDTO;
import com.realtimechat.realtimechat.model.Message;

@Service
public class ChatService {
    private final MessageRepository messageRepository;

    public ChatService(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
    }

    public Message saveMessage(MessageDTO incomingMessage){
        Message message =new Message();
        message.setConversationId(incomingMessage.getConversationId());
        message.setContent(incomingMessage.getContent());
        message.setSenderId(incomingMessage.getSenderId());
        Message savedMessage=messageRepository.save(message);
        return savedMessage;
    }

    public MessageDTO toDTO(Message message,String senderName){
        MessageDTO dto =new MessageDTO();
        dto.setContent(message.getContent());
        dto.setSenderName(senderName);
        dto.setConversationId(message.getConversationId());
        dto.setCreatedAt(message.getCreatedAt());
        dto.setId(message.getId());
        dto.setSenderId(message.getSenderId());
        return dto;
    }
}
