package com.example.demo.service.impl;

import com.example.demo.mapper.UserCustomMapper;
import com.example.demo.repository.UserCustomRespository;
import com.example.demo.service.Dto.UserCustomDTO;
import com.example.demo.service.UserCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserCustomServiceImpl implements UserCustomService {
    @Autowired
    private UserCustomRespository userCustomRespository;

    @Override
    public UserCustomDTO save(UserCustomDTO entityBody) {
        if( entityBody == null) throw new RuntimeException("Erro Objeto nullo");
        if(userCustomRespository.findByEmail(entityBody.getEmail())) throw new RuntimeException("Email ja cadastrado");
        var userCustomSaved = userCustomRespository.save(UserCustomMapper.INSTANCE.toEntity(entityBody));
        return UserCustomMapper.INSTANCE.toDto(userCustomSaved) ;
    }
}
