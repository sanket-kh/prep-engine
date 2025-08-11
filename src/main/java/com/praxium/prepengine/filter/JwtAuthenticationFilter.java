package com.praxium.prepengine.filter;

import com.praxium.prepengine.constant.URIConstants;
import com.praxium.prepengine.security.UserDetail;
import com.praxium.prepengine.service.UserService;
import com.praxium.prepengine.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);

        String authHeader = requestWrapper.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);

            try {
                String email = jwtUtil.extractEmail(jwt);
                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    //todo enhance this by fetching user details from DB if needed
                    UserDetail userDetails = userService.loadUserByEmail(email);
                    if (jwtUtil.isTokenValid(jwt, userDetails)) {
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null,
                                        userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                    responseWrapper.copyBodyToResponse();
                }
            } catch (Exception e) {
                responseWrapper.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                responseWrapper.getWriter().write("Invalid or expired JWT token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

//    @Override
//    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
//        String path = request.getServletPath();
//        Optional<String> matchedPath = Arrays.stream(URIConstants.PUBLIC_ACCESS_URI).filter(
//                uri -> uri.contains(path)
//        ).findFirst();
//        return matchedPath.isPresent();
//    }
}
