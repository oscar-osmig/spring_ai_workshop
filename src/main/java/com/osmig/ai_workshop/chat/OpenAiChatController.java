package com.osmig.ai_workshop.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.logging.Logger;

@RestController
public class OpenAiChatController {
    private final ChatClient chatClient;

    public OpenAiChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }


    @GetMapping("/chat")
    public String chat(){
        return chatClient.prompt()
                .user("Tell me an interesting fact about Java.")
                .call()
                .content();
    }

    @GetMapping("/stream")
    public Flux<String> stream(){
        return chatClient.prompt()
                .user("I'm visiting Miami, can you suggest me 10 places to visit?")
                .stream()
                .content();
    }

    @GetMapping("/joke")
    public ChatResponse joke(){
        return chatClient.prompt()
                .user("Tell me a dad joke about dogs!")
                .call()
                .chatResponse();
    }

}
