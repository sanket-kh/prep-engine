package com.praxium.prepengine.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Authorities {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    ADMIN_READ("librarian:read"),
    ADMIN_WRITE("librarian:write");

    private final String authority;
}
