package com.praxium.prepengine.handler;

import com.praxium.prepengine.entity.User;
import com.praxium.prepengine.mapper.UserMapper;
import com.praxium.prepengine.modal.dto.UserBaseInfoDTO;
import com.praxium.prepengine.util.JwtUtil;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class OAuth2SuccessHandler {
    private final JwtUtil jwtUtil;

    public Map<String, Object> onAuthenticationSuccess(User user) {

        String email = user.getEmail();

        Map<String, Object> claims = new HashMap<>();
        claims.put("displayName", user.getName());
        claims.put("email", user.getEmail());
        claims.put("photoUrl", user.getPhotoUrl());
        String jwt = jwtUtil.generateToken(claims, email);

        Map<String, Object> result = new HashMap<>();
        result.put("token", jwt);
        return result;

    }
}
