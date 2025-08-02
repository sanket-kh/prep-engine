package com.praxium.prepengine.service;

import com.praxium.prepengine.security.UserDetail;
import org.springframework.http.ResponseEntity;

public interface UserService {

     ResponseEntity<Object> getUserBaseInfo();
     UserDetail loadUserByEmail(String email);

    boolean userExist(String email);
}
