package com.praxium.prepengine.mapper;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.praxium.prepengine.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public User mapToUser(GoogleIdToken.Payload payload, String googleId){
      return User.builder()
              .email(payload.getEmail())
              .name((String) payload.get("name"))
              .photoUrl((String) payload.get("picture"))
              .googleId(googleId)
              .build();
    }
}
