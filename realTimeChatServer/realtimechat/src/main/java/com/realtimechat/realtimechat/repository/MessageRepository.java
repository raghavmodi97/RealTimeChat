package com.realtimechat.realtimechat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.realtimechat.realtimechat.model.Message;

public interface MessageRepository extends JpaRepository<Message,Long> {
    
}
