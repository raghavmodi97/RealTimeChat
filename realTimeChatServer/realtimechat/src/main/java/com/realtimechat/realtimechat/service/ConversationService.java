package com.realtimechat.realtimechat.service;

import com.realtimechat.realtimechat.model.Conversation;
import com.realtimechat.realtimechat.model.ConversationParticipant;
import com.realtimechat.realtimechat.repository.ConversationParticipantRepository;
import com.realtimechat.realtimechat.repository.ConversationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConversationService {
    private final ConversationParticipantRepository conversationParticipantRepository;
    private final ConversationRepository conversationRepository;

    public ConversationService(ConversationRepository conversationRepository,
                               ConversationParticipantRepository conversationParticipantRepository) {
        this.conversationRepository = conversationRepository;
        this.conversationParticipantRepository = conversationParticipantRepository;
    }

    public List<Conversation> getConversationsForUser(Long userId){
        List<ConversationParticipant> participants=this.conversationParticipantRepository.findByUserId(userId);
        List<Long> conversationIds =participants.stream().map(ConversationParticipant::getConversationId).toList();
        List<Conversation> conversations=this.conversationRepository.findAllById(conversationIds);
        return conversations;

    }
}
