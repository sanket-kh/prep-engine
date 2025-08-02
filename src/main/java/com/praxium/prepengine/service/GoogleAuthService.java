package com.praxium.prepengine.service;

import org.springframework.http.ResponseEntity;

public interface GoogleAuthService {
    ResponseEntity<Object> authenticateViaGoogle(String idTokenString);
}
