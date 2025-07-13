package com.osmig.ai_workshop.byod;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelComparison {
    private final ChatClient chatClient;

    public ModelComparison(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    /**
     * Handles GET requests to "/models".
     *
     * Uses the ChatClient to prompt for an up-to-date list of popular large language models
     * and their context windows. Returns the response content as a String.
     *
     * @return a String containing the list of popular large language models and their context windows
     */
    @GetMapping("/models")
    public String models(){
        return chatClient.prompt()
                .user("Can you give me and up to date list of popular large language models and context windows?")
                .call()
                .content();
    }

    @GetMapping("/models/stuff-the-prompt")
    public String modelsStuffThePrompt(){
        var system = """
                if you are asked about an up to date list popular large language models and their current context windows
                
                [
                    {"company": "OpenAI", "model":"GPT-4o", "context_window_size": 128000 },
                    {"company": "OpenAI", "model":"o1-preview", "context_window_size": 128000 },

                    {"company": "Anthropic", "model":"Claude Opus 4", "context_window_size": 200000 },
                    {"company": "Anthropic", "model":"Claude Sonnet 4", "context_window_size": 200000 },
                    
                    {"company": "Google", "Gemini 2.5 Pro", "context_window_size": 100000 },
                    {"company": "Google", "Gemini 2.5 Pro (Exp.)", "context_window_size": 200000},
                    {"company": "Google", "Gemini 2.5 Flash", "context_window_size": 100000 },
                
                """;


        return chatClient.prompt()
                .user("Can you give me and up to date list of popular large language models and their context windows?")
                .system(system)
                .call()
                .content();
    }
}
