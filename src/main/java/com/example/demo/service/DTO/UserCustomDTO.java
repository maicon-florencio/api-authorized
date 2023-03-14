package com.example.demo.service.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCustomDTO implements Serializable {

    private Long idUser;
    private String fisrtName;
    private String fullName;
    private String password;
    private Boolean enabled;

}
