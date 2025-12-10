package com.realtimechat.realtimechat.dto;

import java.time.Instant;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypingDTO {
    private Long conversationId;
    private Long senderId;
    private boolean typing;
}
