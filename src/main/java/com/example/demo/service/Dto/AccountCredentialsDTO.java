package com.example.demo.service.Dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class AccountCredentialsDTO implements Serializable {

        private String email;
        private String password;

    public AccountCredentialsDTO(){}
    public AccountCredentialsDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
