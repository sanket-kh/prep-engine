package com.praxium.prepengine.util;


import com.praxium.prepengine.constant.ResponseCode;
import com.praxium.prepengine.constant.ResponseMessage;
import com.praxium.prepengine.modal.response.DefaultServerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtility {
    public static ResponseEntity<Object> successResponseWithBody( Object responseBody) {
        DefaultServerResponse defaultServerResponse = new DefaultServerResponse();
        defaultServerResponse.setSuccess(true);
        defaultServerResponse.setResponseBody(responseBody);
        defaultServerResponse.setResponseCode(ResponseCode.SUCCESS_CODE);
        return new ResponseEntity<>(defaultServerResponse, HttpStatus.OK);

    }

    public static ResponseEntity<Object> failureResponseWithBody(String statusCode, Object responseBody) {
        DefaultServerResponse defaultServerResponse = new DefaultServerResponse();
        defaultServerResponse.setSuccess(true);
        defaultServerResponse.setResponseBody(responseBody);
        defaultServerResponse.setResponseCode(statusCode);
        return new ResponseEntity<>(defaultServerResponse, HttpStatus.OK);
    }

    public static ResponseEntity<Object> successResponseWithMessageAndBody(String statusCode, String message, Object responseBody) {
        DefaultServerResponse defaultServerResponse = new DefaultServerResponse();
        defaultServerResponse.setSuccess(true);
        defaultServerResponse.setResponseBody(responseBody);
        defaultServerResponse.setMessage(message);
        defaultServerResponse.setResponseCode(statusCode);
        return new ResponseEntity<>(defaultServerResponse, HttpStatus.OK);


    }

    public static ResponseEntity<Object> failureResponseWithMessageAndBody(String statusCode, String message, Object responseBody) {
        DefaultServerResponse defaultServerResponse = new DefaultServerResponse();
        defaultServerResponse.setSuccess(false);
        defaultServerResponse.setResponseBody(responseBody);
        defaultServerResponse.setMessage(message);
        defaultServerResponse.setResponseCode(statusCode);
        return new ResponseEntity<>(defaultServerResponse, HttpStatus.OK);
    }

    public static ResponseEntity<Object> successResponseWithMessage(String statusCode, String message) {
        DefaultServerResponse defaultServerResponse = new DefaultServerResponse();
        defaultServerResponse.setSuccess(true);
        defaultServerResponse.setMessage(message);
        defaultServerResponse.setResponseCode(statusCode);
        return new ResponseEntity<>(defaultServerResponse, HttpStatus.OK);
    }

    public static ResponseEntity<Object> failureResponseWithMessage(String statusCode, String message) {
        DefaultServerResponse defaultServerResponse = new DefaultServerResponse();
        defaultServerResponse.setSuccess(false);
        defaultServerResponse.setMessage(message);
        defaultServerResponse.setResponseCode(statusCode);
        return new ResponseEntity<>(defaultServerResponse, HttpStatus.OK);
    }
    public static ResponseEntity<Object> exceptionResponse() {
        DefaultServerResponse defaultServerResponse = new DefaultServerResponse();
        defaultServerResponse.setSuccess(false);
        defaultServerResponse.setMessage(ResponseMessage.SERVER_ERROR);
        defaultServerResponse.setResponseCode(ResponseCode.FAILURE_CODE);
        return new ResponseEntity<>(defaultServerResponse, HttpStatus.OK);
    }
}

