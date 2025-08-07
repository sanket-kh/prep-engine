package com.praxium.prepengine.service;

import com.praxium.prepengine.modal.dto.QuestionBaseDTO;
import com.praxium.prepengine.modal.request.AptitudeQuestionRequest;

public interface ChatService {

    QuestionBaseDTO getAptitudeQuestion(AptitudeQuestionRequest aptitudeQuestionRequest);
}
