package com.realtimechat.realtimechat.model;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.Instant;
import jakarta.persistence.*;

@Entity
@Table(name="conversation")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Instant createdAt = Instant.now();

}
