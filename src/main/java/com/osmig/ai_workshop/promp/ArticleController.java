package com.osmig.ai_workshop.promp;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {
    private final ChatClient chatClient;

    public ArticleController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/post/new")
    public String newPost(@RequestParam(value = "topic", defaultValue = "JDK Virtual Threads") String topic) {
        var systemInstruction = """
                1. Length & Purpose: Generate 500-word blog posts that inform and engage general audiences.
                
                2. Structure:
                   - Introduction: Hook renders and establish the topic's relevance.
                   - Body: Develop 3 main points with supporting evidence and examples.
                   - Conclusion: Summarize key takeaways and include a call-to-action.
                
                3. Content Requirements:
                   - Include real-world applications or case studies.
                   - Incorporate relevant statistics or data points when appropriate.
                   - Explain benefits/implementations clearly for non-experts.
                
                4. Tone & Style:
                   - Write in an informative yet conversational voice.
                   - Use accessible language while maintaining authority.
                   - Break up text with subheadings and short paragraphs.
                
                5. Response Format:
                   - Deliver complete, ready-to-publish posts with a suggested title.
                """;

        return chatClient.prompt()
                .user(u -> {
                    u.text("Write a blog about {topic}");
                    u.param("topic", topic);
                })
                .system(systemInstruction)
                .call()
                .content();
    }
}
