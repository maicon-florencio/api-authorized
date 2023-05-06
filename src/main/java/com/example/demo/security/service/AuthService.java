package com.example.demo.security.service;

import com.example.demo.Util.ErrorMsgUtil;
import com.example.demo.repository.UserCustomRespository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.Dto.AccountCredentialsDTO;
import com.example.demo.service.Dto.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private UserCustomRespository respository;
    @SuppressWarnings("rawtypes")
    public ResponseEntity signin(AccountCredentialsDTO data){
        if(!Objects.nonNull(data)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorMsgUtil.ERRO_INVALID_CLIENT_RESQUEST);
        try{
            var email =  data.getEmail();
            var password = data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
            var user = respository.findUserCustomByEmail(email);
             var tokenResponse = new TokenDTO();
             if(user != null){
                 tokenResponse = tokenProvider.createAcessToken(email,user.getRoles());
             }else {
                 throw new UsernameNotFoundException("Email " + email + " not found");
             }
             if(!Objects.nonNull(tokenResponse)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorMsgUtil.ERRO_INVALID_CLIENT_RESQUEST);
        return ResponseEntity.ok(tokenResponse);
        }catch (Exception e){
            throw new BadCredentialsException(ErrorMsgUtil.ERRO_INVALID_CLIENT_PASSWORD);
        }
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String email, String refreshToken) {
        var user = respository.findUserCustomByEmail(email);
        if(checkIfParamsIsNotNull(email,refreshToken)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        var tokenResponse = new TokenDTO();
        if (user != null) {
            tokenResponse = tokenProvider.refreshToken(refreshToken);
            if(tokenResponse == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        } else {
            throw new UsernameNotFoundException(String.format("Username s% not found!", email));
        }
        return ResponseEntity.ok(tokenResponse);
    }

    private boolean checkIfParamsIsNotNull(String username, String refreshToken) {
        return refreshToken == null || refreshToken.isBlank() ||
                username == null || username.isBlank();
    }

    private boolean checkIfParamsIsNotNull(AccountCredentialsDTO data) {
        return data == null || data.getEmail() == null || data.getEmail().isBlank()
                || data.getPassword() == null || data.getPassword().isBlank();
    }
}
