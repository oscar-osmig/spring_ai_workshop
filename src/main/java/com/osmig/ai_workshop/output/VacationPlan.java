package com.osmig.ai_workshop.output;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VacationPlan {
    private ChatClient chatClient;

    public VacationPlan(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/vacation/unstructured")
    public String unstructured(){
        return chatClient.prompt()
                .user("I want to plan a vacation to Miami, Florida. I want to visit the beach, go to a museum, and try some local food. Can you help me with that?")
                .call()
                .content();
    }

    @GetMapping("/vacation/structured")
    public Itinanery structured(){
        return chatClient.prompt()
                .user("I want to plan a vacation to Miami, Florida. I want to visit the beach, go to a museum, and try some local food. Can you help me with that?")
                .call()
                .entity(Itinanery.class);
    }

}
