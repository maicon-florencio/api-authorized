package com.example.demo.builder;

import com.example.demo.dominio.UserCustom;
import com.example.demo.service.Dto.UserCustomDTO;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class UserCustomBuilderTest {

    public UserCustom returnUserOkAtivo(){
        return UserCustom.builder()
                .idUser(1L)
                .firstName("JOSE")
                .fullName("S Quieroz")
                .email("teste@gmail.com")
                .password("banana")
                .enabled(Boolean.TRUE)
                .accountNonExpired(Boolean.TRUE)
                .accountNonLocked(Boolean.TRUE)
                .createdAT(new Date())
                .build();
    }

    public UserCustomDTO returnUserDTOOKAtivo(){
        return UserCustomDTO.builder()
                .idUser(3L)
                .firstName("Martia")
                .fullName("Sanchez")
                .email("teste@gmail.com")
                .password("Cocacola")
                .enabled(Boolean.TRUE)
                .accountNonExpired(Boolean.TRUE)
                .accountNonLocked(Boolean.TRUE)
                .createdAT(new Date())
                .createdAT(new Date())
                .build();
    }


    public List<UserCustom> returnListUserOkAtivo(){
        return Arrays.asList( UserCustom.builder()
                .idUser(1L)
                .firstName("JOSE")
                .fullName("S Quieroz")
                .email("teste4@gmail.com")
                .password("banana")
                .enabled(Boolean.TRUE)
                    .build(), UserCustom.builder()
                    .idUser(2L)
                    .firstName("JOAQUIM")
                    .fullName("S AZEVEDO")
                .email("teste5@gmail.com")
                    .password("CABRIOXA")
                    .enabled(Boolean.TRUE)
                    .build(), UserCustom.builder()
                    .idUser(3L)
                    .firstName("PEDRO")
                    .fullName("ALVEZ")
                .email("test61@gmail.com")
                    .password("PERA")
                    .enabled(Boolean.TRUE)
                    .build());
    }

    public List<UserCustomDTO> returnListUserDtoOkAtivo(){
        return Arrays.asList( UserCustomDTO.builder()
                .idUser(1L)
                .firstName("JOSEdto")
                .fullName("S Quieroz dto")
                        .email("teste1@gmail.com")
                .password("banana")
                .enabled(Boolean.TRUE)
                .build(), UserCustomDTO.builder()
                .idUser(2L)
                .firstName("JOAQUIM dto")
                .fullName("S AZEVEDO dto")
                .email("teste2@gmail.com")
                .password("CABRIOXA")
                .enabled(Boolean.TRUE)
                .build(), UserCustomDTO.builder()
                .idUser(3L)
                .firstName("PEDRO dto")
                .fullName("ALVEZ dto")
                .email("teste3@gmail.com")
                .password("PERA")
                .enabled(Boolean.TRUE)
                .build());
    }

}
