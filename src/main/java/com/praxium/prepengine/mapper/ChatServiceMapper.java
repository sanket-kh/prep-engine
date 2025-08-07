package com.praxium.prepengine.mapper;

import com.praxium.prepengine.enums.QuestionType;
import com.praxium.prepengine.modal.request.AptitudeQuestionRequest;
import com.praxium.prepengine.modal.request.PromptRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ChatServiceMapper {

    public PromptRequest mapToPromptRequest(AptitudeQuestionRequest aptitudeQuestionRequest){
        return PromptRequest.builder()
                .mode(QuestionType.MCQ.name())
                .product(aptitudeQuestionRequest.getProduct())
                .questionsToFetch(5)
                .build();
    }
}
