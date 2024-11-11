package com.example.demo.controller.service;

import com.example.demo.controller.dto.ChatRequest;
import dev.langchain4j.service.SystemMessage;

public interface GenAIService {
    @SystemMessage(
            """
                    You are a construction assistant. Respond in providing your suggestion on what number of machines to use and which ones to use for each request.
                    """
    )
//    String chat(@MemoryId int memoryId, @UserMessage String userMessage);
    String getResponse(ChatRequest request);
}
