package com.example.demo.service.Dto;

import com.example.demo.dominio.Permissao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
    private boolean accountNonExpired ;

    private List<Permissao> permissoes;

}
