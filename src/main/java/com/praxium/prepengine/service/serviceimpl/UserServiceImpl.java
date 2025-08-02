package com.praxium.prepengine.service.serviceimpl;

import com.praxium.prepengine.entity.User;
import com.praxium.prepengine.repository.UserRepo;
import com.praxium.prepengine.security.UserDetail;
import com.praxium.prepengine.service.UserService;
import com.praxium.prepengine.util.ResponseUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;


    @Override
    public ResponseEntity<Object> getUserBaseInfo() {
        try {
            return null;
        }catch (Exception e){
            log.error("UserServiceImpl :: UserService",e);
            return ResponseUtility.exceptionResponse();
        }
    }

    @Override
    public UserDetail loadUserByEmail(String email) {
       return new UserDetail(userRepo.findByEmail(email));
    }

    @Override
    public ResponseEntity<Object> authenticateUser() {
        try {

            return null;
        }catch (Exception e){
            log.error("UserServiceImpl :: authenticateUser",e);
            return ResponseUtility.exceptionResponse();
        }
    }

    @Override
    public boolean userExist(String email) {
        return userRepo.findByEmailExists(email);
    }

}