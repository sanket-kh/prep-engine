package com.praxium.prepengine.controller;

import com.praxium.prepengine.service.GoogleAuthService;
import com.praxium.prepengine.service.UserService;
import com.praxium.prepengine.util.ResponseUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final GoogleAuthService googleAuthService;

    @GetMapping("/user/detail")
    public ResponseEntity<Object> retrieveUser() {
        try {
            return userService.getUserBaseInfo();
        } catch (Exception e) {
            log.error("UserController :: retrieveUser", e);
            return ResponseUtility.exceptionResponse();
        }
    }
    @PostMapping("/user/login")
    public ResponseEntity<Object> authenticateUser(@RequestParam("tokenId") String tokenId) {
        try {
            return googleAuthService.authenticateViaGoogle(tokenId);
        } catch (Exception e) {
            log.error("UserController :: authenticateUser", e);
            return ResponseUtility.exceptionResponse();
        }
    }
}
