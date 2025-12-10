package com.realtimechat.realtimechat.repository;

import com.realtimechat.realtimechat.model.ConversationParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversationParticipantRepository extends JpaRepository<ConversationParticipant,Long> {
    List<ConversationParticipant> findByUserId(Long userId);
}
