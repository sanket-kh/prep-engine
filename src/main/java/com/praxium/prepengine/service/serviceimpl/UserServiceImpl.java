package com.praxium.prepengine.service.serviceimpl;

import com.praxium.prepengine.constant.ResponseCode;
import com.praxium.prepengine.entity.User;
import com.praxium.prepengine.repository.UserRepo;
import com.praxium.prepengine.security.UserDetail;
import com.praxium.prepengine.service.UserService;
import com.praxium.prepengine.util.ObjectUtils;
import com.praxium.prepengine.util.JwtUtil;
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
    private final JwtUtil jwtUtil;


    @Override
    public ResponseEntity<Object> getUserBaseInfo() {
        try {
            User userFromToken = jwtUtil.getLoggedInUser();
            if (ObjectUtils.isEmpty(userFromToken)){
                return ResponseUtility.failureResponseWithMessage(ResponseCode.FAILURE_CODE,"User doesnt exist");
            }

            return ResponseUtility.successResponseWithBody(userFromToken);
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
    public boolean userExist(String email) {
        User byEmail = userRepo.findByEmail(email);
        return ObjectUtils.isNotEmpty(byEmail);
    }

}