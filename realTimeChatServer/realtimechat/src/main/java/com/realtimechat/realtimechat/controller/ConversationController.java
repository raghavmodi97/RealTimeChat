package com.realtimechat.realtimechat.controller;

import com.realtimechat.realtimechat.dto.ConversationSummaryDTO;
import com.realtimechat.realtimechat.service.ConversationService;
import com.realtimechat.realtimechat.model.Conversation;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//It tells Spring:
//“This class exposes REST endpoints, and methods will return JSON (or other body), not HTML pages.”
//So any method in this class that returns a Java object will automatically be converted to JSON and sent back in HTTP response.
@RequestMapping("/api/conversations")//Base path: /api/conversations
//Method path: /user/{userId}
//Together they form the final URL:
//GET /api/conversations/user/{userId}
//This is what React app will call later.
public class ConversationController {
    private final ConversationService conversationService;
    public ConversationController(ConversationService conversationService){
        this.conversationService=conversationService;
    }

    @GetMapping("/user/{userId}")//“This method handles HTTP GET requests to /api/conversations/user/{userId}”.
    public List<ConversationSummaryDTO> getForUser(@PathVariable Long userId){//@PathVariable binds {userId} from the URL path into the Java method parameter.
            List<Conversation> conversations= this.conversationService.getConversationsForUser(userId);
            List<ConversationSummaryDTO> dtoList=conversations.stream().map(c -> new ConversationSummaryDTO(
                c.getId(),
                c.getTitle(),
                c.getCreatedAt()
            )).toList();
            return dtoList;
    }
}
