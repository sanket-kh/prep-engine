package com.praxium.prepengine.service.serviceimpl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.praxium.prepengine.entity.User;
import com.praxium.prepengine.handler.OAuth2SuccessHandler;
import com.praxium.prepengine.mapper.UserMapper;
import com.praxium.prepengine.repository.UserRepo;
import com.praxium.prepengine.service.GoogleAuthService;
import com.praxium.prepengine.service.UserService;
import com.praxium.prepengine.util.ResponseUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Log4j2
@Service
@RequiredArgsConstructor
public class GoogleAuthServiceImpl implements GoogleAuthService {
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final UserService userService;
    private final UserRepo userRepo;
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Override
    public ResponseEntity<Object> authenticateViaGoogle(String idTokenString) {

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                .Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(clientId))
                .build();

        try {
            GoogleIdToken idToken = verifier.verify(idTokenString);
            Payload payload = idToken.getPayload();

            User user = UserMapper.mapToUser(payload, idTokenString);
            if (!userService.userExist(user.getEmail())){
                userRepo.save(user);
            }
            return ResponseUtility.successResponseWithBody(oAuth2SuccessHandler.onAuthenticationSuccess(user));
        } catch (Exception e) {
            log.error("GoogleAuthServiceImpl :: authenticateViaGoogle", e);
            return ResponseUtility.exceptionResponse();
        }
    }

}