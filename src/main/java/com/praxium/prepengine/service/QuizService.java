package com.praxium.prepengine.service;

import com.praxium.prepengine.modal.dto.QuestionBaseDTO;
import com.praxium.prepengine.modal.request.AptitudeQuestionRequest;
import org.springframework.http.ResponseEntity;

public interface QuizService {

    ResponseEntity<Object> getAptitudeQuiz(AptitudeQuestionRequest aptitudeQuestionRequest);
}
