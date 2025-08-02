package com.praxium.prepengine.modal.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultServerResponse {
    private String responseCode;
    private String message;
    private boolean success;
    private Object responseBody;
}
