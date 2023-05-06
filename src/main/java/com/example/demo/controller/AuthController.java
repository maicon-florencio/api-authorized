package com.example.demo.controller;

import com.example.demo.security.service.AuthService;
import com.example.demo.service.Dto.AccountCredentialsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @SuppressWarnings("rawtypes")
    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody AccountCredentialsDTO data){
       return this.authService.signin(data);
    }


    @SuppressWarnings("rawtypes")
    @PutMapping(value = "/refresh/{email}")
    public ResponseEntity refreshToken(@PathVariable("email") String email,
                                       @RequestHeader("Authorization") String refreshToken) {
        return this.authService.refreshToken(email,refreshToken);
    }
}
