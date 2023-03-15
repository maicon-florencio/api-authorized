package com.example.demo.builder;

import com.example.demo.dominio.UserCustom;
import com.example.demo.service.DTO.UserCustomDTO;
import org.springframework.stereotype.Component;

@Component
public class UserCustomBuilderTest {

    public UserCustom returnUserOkAtivo(){
        return UserCustom.builder()
                .idUser(1L)
                .firstName("JOSE")
                .fullName("S Quieroz")
                .password("banana")
                .enabled(Boolean.TRUE)
                .build();
    }

    public UserCustomDTO returnUserDTOOKAtivo(){
        return UserCustomDTO.builder()
                .idUser(3L)
                .firstName("Martia")
                .fullName("Sanchez")
                .password("Cocacola")
                .enabled(Boolean.TRUE)
                .build();
    }
}
