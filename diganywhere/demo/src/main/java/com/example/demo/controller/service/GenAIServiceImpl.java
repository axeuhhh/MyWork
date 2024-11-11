package com.example.demo.controller.service;

import com.example.demo.controller.dto.ChatRequest;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenAIServiceImpl implements GenAIService{

    private final Assistant assistant;


    @Override
    public String getResponse(ChatRequest request) {
        return assistant.chat(request.userId(), request.question());

    }
    public String getResponseSimple(ChatRequest request) {
        List<ChatMessage> messages = new ArrayList<>();
        messages.add(SystemMessage.systemMessage("Respond in French"));
        messages.add(UserMessage.userMessage(request.question()));
        var model = OpenAiChatModel.builder()
                .apiKey("demo")
                .modelName(OpenAiChatModelName.GPT_4_O_MINI)
                .build();
        System.out.println(messages.toString());
        return model.generate(messages).content().text();
    }

}
