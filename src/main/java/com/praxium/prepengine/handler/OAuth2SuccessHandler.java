package com.praxium.prepengine.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.praxium.prepengine.entity.User;
import com.praxium.prepengine.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizationSuccessHandler;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@RequiredArgsConstructor
public class OAuth2SuccessHandler {
    private final JwtUtil jwtUtil;

    public Map<String, Object> onAuthenticationSuccess(User user) {

        String email = user.getEmail();

        Map<String, Object> claims = new HashMap<>();
        claims.put("user", user);

        String jwt = jwtUtil.generateToken(claims, email);

        Map<String, Object> result = new HashMap<>();
        result.put("token", jwt);
        return result;

    }
}
