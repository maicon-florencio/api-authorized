package com.example.demo.security.service;

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
        if(!Objects.nonNull(data)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Cliente Request");
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
             if(!Objects.nonNull(tokenResponse)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Cliente Request");
        return ResponseEntity.ok(tokenResponse);
        }catch (Exception e){
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
