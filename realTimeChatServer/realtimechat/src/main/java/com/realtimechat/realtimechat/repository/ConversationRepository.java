package com.realtimechat.realtimechat.repository;

import com.realtimechat.realtimechat.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation,Long> {
}
