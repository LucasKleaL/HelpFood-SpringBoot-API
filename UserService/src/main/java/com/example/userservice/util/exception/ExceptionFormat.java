package com.example.userservice.util.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionFormat {

    @ExceptionHandler({MessageException.class, RuntimeException.class})
    public ResponseEntity<Object> sqlError(MessageException exception) {
        String result = "{" +
                "\"code\":\""+exception.getCode()+"\"," +
                "\"message\":\""+exception.getMessage()+"\"" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<Object>(result, headers, HttpStatus.BAD_REQUEST);
    }

}