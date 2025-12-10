package com.realtimechat.realtimechat.config;

import com.realtimechat.realtimechat.model.Conversation;
import com.realtimechat.realtimechat.model.ConversationParticipant;
import com.realtimechat.realtimechat.repository.ConversationParticipantRepository;
import com.realtimechat.realtimechat.repository.ConversationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ConversationRepository conversationRepository;
    private final ConversationParticipantRepository conversationParticipantRepository;
    public DataInitializer(ConversationRepository conversationRepository,ConversationParticipantRepository conversationParticipantRepository){
        this.conversationRepository=conversationRepository;
        this.conversationParticipantRepository=conversationParticipantRepository;
    }
    @Override
    public void run(String... args) throws Exception{
        if (conversationRepository.count() > 0) {
            return;
        }
        Conversation chatWithAlice=new Conversation();
        chatWithAlice.setTitle("Chat With Alice");
        Conversation studyGroup=new Conversation();
        studyGroup.setTitle("Study Group");

        chatWithAlice=conversationRepository.save(chatWithAlice);
        studyGroup=conversationRepository.save(studyGroup);

        ConversationParticipant p1=new ConversationParticipant();
        p1.setConversationId(chatWithAlice.getId());
        p1.setUserId(1L);

        ConversationParticipant p2=new ConversationParticipant();
        p2.setConversationId(chatWithAlice.getId());
        p2.setUserId(2L);

        ConversationParticipant p3=new ConversationParticipant();
        p3.setConversationId(studyGroup.getId());
        p3.setUserId(3L);

        ConversationParticipant p4=new ConversationParticipant();
        p4.setConversationId(studyGroup.getId());
        p4.setUserId(1L);

        conversationParticipantRepository.save(p1);
        conversationParticipantRepository.save(p2);
        conversationParticipantRepository.save(p3);
        conversationParticipantRepository.save(p4);
    }
}
