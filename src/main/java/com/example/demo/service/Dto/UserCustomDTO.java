package com.example.demo.service.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCustomDTO implements Serializable {

    private Long idUser;
    private String firstName;
    private String email;
    private String fullName;
    private String password;
    private Boolean enabled;
    private Date createdAT;

    private boolean account_non_expored = Boolean.TRUE;
    private boolean account_non_locked = Boolean.TRUE;
    private boolean account_non_expired = Boolean.TRUE;

}
