package com.realtimechat.realtimechat.model;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Entity
@Table(name="conversation_participants")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConversationParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long conversationId;
    private Long userId;
    private Instant joinedAt=Instant.now();
}
