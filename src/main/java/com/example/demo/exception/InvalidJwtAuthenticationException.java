package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAuthenticationException {

  /*  private static final long serialVersionUID = 1l;
    public InvalidJwtAuthenticationException(String msg){
        super(msg);
    }*/
}
