package com.praxium.prepengine.service.serviceimpl;

import com.praxium.prepengine.constant.ResponseCode;
import com.praxium.prepengine.entity.User;
import com.praxium.prepengine.modal.request.AptitudeQuestionRequest;
import com.praxium.prepengine.service.ChatService;
import com.praxium.prepengine.service.QuizService;
import com.praxium.prepengine.util.JwtUtil;
import com.praxium.prepengine.util.ObjectUtils;
import com.praxium.prepengine.util.ResponseUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final JwtUtil jwtUtil;
    private final ChatService chatService;


    @Override
    public ResponseEntity<Object> getAptitudeQuiz(AptitudeQuestionRequest aptitudeQuestionRequest) {
        try {
            User loggedInUser = jwtUtil.getLoggedInUser();
            if (ObjectUtils.isEmpty(loggedInUser)) {
                return ResponseUtility.failureResponseWithMessage(ResponseCode.RESOURCE_NOT_FOUND, "User not found");
            }
            chatService.getAptitudeQuestion(aptitudeQuestionRequest);


            return null;
        } catch (Exception e) {
            log.error("QuizServiceImpl :: getAptitudeQuestions", e);
            return ResponseUtility.exceptionResponse();
        }
    }
}
