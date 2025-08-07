package com.praxium.prepengine.service.serviceimpl;

import com.praxium.prepengine.enums.QuestionType;
import com.praxium.prepengine.mapper.ChatServiceMapper;
import com.praxium.prepengine.modal.dto.QuestionBaseDTO;
import com.praxium.prepengine.modal.request.AptitudeQuestionRequest;
import com.praxium.prepengine.modal.request.PromptRequest;
import com.praxium.prepengine.service.ChatService;
import com.praxium.prepengine.util.PromptGeneratorUtil;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatClient chatClient;

    public ChatServiceImpl(ChatClient.Builder builder) {
    chatClient = builder.build();
    }

    @Override
    public QuestionBaseDTO getAptitudeQuestion(AptitudeQuestionRequest aptitudeQuestionRequest) {
        PromptRequest promptRequest = ChatServiceMapper.mapToPromptRequest(aptitudeQuestionRequest);
        String prompt = PromptGeneratorUtil.buildPrompt(promptRequest);
        String jsonString = chatClient.prompt(prompt).call().content();
        System.out.println(jsonString);
        return null;
    }
}