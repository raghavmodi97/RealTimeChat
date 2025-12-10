package com.realtimechat.realtimechat.dto;

import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConversationSummaryDTO {
    private Long id;
    private String title;
    private Instant createdAt;
}
