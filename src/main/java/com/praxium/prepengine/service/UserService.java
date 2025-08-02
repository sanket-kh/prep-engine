package com.praxium.prepengine.service;

import com.praxium.prepengine.entity.User;
import com.praxium.prepengine.modal.dto.UserBaseInfoDTO;
import com.praxium.prepengine.modal.response.DefaultServerResponse;
import com.praxium.prepengine.security.UserDetail;
import org.springframework.http.ResponseEntity;

public interface UserService {

     ResponseEntity<Object> getUserBaseInfo();
     UserDetail loadUserByEmail(String email);

     ResponseEntity<Object> authenticateUser();

    boolean userExist(String email);
}
