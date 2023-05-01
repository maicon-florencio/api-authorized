package com.example.demo.service.Dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TokenDTO implements Serializable {

    private String username;
    private String email;
    private Boolean auhenticated;
    private Date created;
    private Date expiration;
    private String acessToken;
    private String refreshToken;

    public TokenDTO(String username, String email, Boolean auhenticated, Date created, Date expiration, String acessToken, String refreshToken) {
        this.username = username;
        this.email = email;
        this.auhenticated = auhenticated;
        this.created = created;
        this.expiration = expiration;
        this.acessToken = acessToken;
        this.refreshToken = refreshToken;
    }
}
