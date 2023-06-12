package com.example.demo.service.impl;

import com.example.demo.dominio.UserCustom;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.UserCustomMapper;
import com.example.demo.repository.UserCustomRespository;
import com.example.demo.service.Dto.UserCustomDTO;
import com.example.demo.service.UserCustomService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserCustomServiceImpl implements UserCustomService {
    private final UserCustomRespository userCustomRespository;
    Logger log = LoggerFactory.getLogger(UserCustomServiceImpl.class);

    public UserCustomServiceImpl(UserCustomRespository userCustomRespository) {
        this.userCustomRespository = userCustomRespository;
    }

    @Override
    public UserCustomDTO save(UserCustomDTO entityBody) {
        log.info("------- Inicio criacao novo user auth ------");
        validatedObject(entityBody);
        if(findUserByEmail(entityBody.getEmail()) != null) throw new BusinessException("Erro : email ja cadastrado");
        var userCustomSaved = userCustomRespository.save(UserCustomMapper.INSTANCE.toEntity(entityBody));
        log.info("------- Fim  criacao novo user auth ------");
        return UserCustomMapper.INSTANCE.toDto(userCustomSaved) ;
    }

    @Override
    public UserCustomDTO findById(Long id) {
       if(!userCustomRespository.existsById(id)) {
           throw new BusinessException(String.format("Erro: %d não encontrado", id));
       }else{
           return UserCustomMapper.INSTANCE.toDto(userCustomRespository.getReferenceById(id));
       }
    }


    private static void validatedNullObjeto(UserCustomDTO entityBody) {
        if( entityBody == null) throw new BusinessException("Erro: Objeto nulo");
    }
    private static void validatedObjectWithOutEmail(UserCustomDTO entityBody) {
        if( entityBody == null) throw new BusinessException("Erro: email null");
    }

    private void validatedObject(UserCustomDTO entityBody){
        log.info("------- Inicio validacao de novo auth ------");
        validatedNullObjeto(entityBody);
        validatedObjectWithOutEmail(entityBody);
        log.info("------- Fim validacao de novo auth ------");
    }
    @Override
    public UserCustomDTO findUserByEmail(String emailSearch){
        var userFound = userCustomRespository.findUserCustomByEmail(emailSearch);
        if(userFound ==null)
            throw new BusinessException(" User not found by email : " + emailSearch);
        return UserCustomMapper.INSTANCE.toDto(userFound);
    }

    @Override
    public UserCustom loadUserByEmail(String email) {
        var userFound = userCustomRespository.findUserCustomByEmail(email);
        if (userFound == null) throw new BusinessException(" User not found by email : " + email);
        return userFound;
    }
}
