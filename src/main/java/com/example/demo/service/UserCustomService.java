package com.example.demo.service;

import com.example.demo.service.DTO.UserCustomDTO;

public interface UserCustomService {

    UserCustomDTO findByID(Long userId);
}
