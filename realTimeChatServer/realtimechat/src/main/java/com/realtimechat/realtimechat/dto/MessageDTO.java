package com.realtimechat.realtimechat.dto;

import java.time.Instant;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private Long id;
    @NotNull(message = "conversationId is required")
    private Long conversationId;
    @NotNull(message = "senderId is required")
    private Long senderId;
    private String senderName;
    @NotBlank(message = "Message content cannot be empty")
    private String content;
    private Instant createdAt;
    private String tempId;
}
