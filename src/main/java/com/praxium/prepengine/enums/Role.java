package com.praxium.prepengine.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static com.praxium.prepengine.enums.Authorities.*;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER(
            Set.of(USER_READ,USER_WRITE)
    ),
    ADMIN(
            Set.of(ADMIN_READ, ADMIN_WRITE)
    );

    private final Set<Authorities> authorities;


}
