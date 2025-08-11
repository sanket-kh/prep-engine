package com.praxium.prepengine.modal.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserBaseInfoDTO {
    private String displayName;
    private String email;
    private String photoUrl;
}
