package com.example.demo.service;

import com.example.demo.dominio.UserCustom;
import com.example.demo.service.Dto.UserCustomDTO;
import org.springframework.http.ResponseEntity;

public interface UserCustomService {

    UserCustomDTO save(UserCustomDTO dto);

    UserCustomDTO findById(Long id);

    UserCustomDTO findUserByEmail(String email);

    UserCustom loadUserByEmail(String email);
}
