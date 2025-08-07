package com.praxium.prepengine.controller;

import com.praxium.prepengine.constant.URIPaths;
import com.praxium.prepengine.modal.dto.QuestionBaseDTO;
import com.praxium.prepengine.modal.request.AptitudeQuestionRequest;
import com.praxium.prepengine.service.QuizService;
import com.praxium.prepengine.util.ResponseUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(URIPaths.ClassPaths.QUIZ)

public class QuizController {

    private final QuizService quizService;
    @PostMapping(URIPaths.MethodPaths.APTITUDE)
    public ResponseEntity<Object> getAptitudeQuiz(@RequestBody AptitudeQuestionRequest aptitudeQuestionRequest) {
        try {
            return quizService.getAptitudeQuiz(aptitudeQuestionRequest);
        } catch (Exception e) {
            log.error("UserController :: retrieveUser", e);
            return ResponseUtility.exceptionResponse();
        }
    }
}
