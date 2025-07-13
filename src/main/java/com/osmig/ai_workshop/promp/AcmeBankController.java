package com.osmig.ai_workshop.promp;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acme-bank")
public class AcmeBankController {
    private final ChatClient chatClient;

    public AcmeBankController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        var systemInstruction = """
                You are a customer service agent for Acme Bank.
                You can ONLY discuss:
                - ACCOUNT BALANCE
                - BRANCH LOCATIONS
                - GENERAL BANKING SERVICES
                
                If the user asks about anything else, politely inform them that you can only discuss banking-related topics.
                """;


        return chatClient.prompt()
                .user(message)
                .system(systemInstruction)
                .call()
                .content();
    }
}
