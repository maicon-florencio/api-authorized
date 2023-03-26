package com.example.demo.service.impl;

import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.UserCustomMapper;
import com.example.demo.repository.UserCustomRespository;
import com.example.demo.service.Dto.UserCustomDTO;
import com.example.demo.service.UserCustomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
        var userCustomSaved = userCustomRespository.save(UserCustomMapper.INSTANCE.toEntity(entityBody));
        log.info("------- Fim  criacao novo user auth ------");
        return UserCustomMapper.INSTANCE.toDto(userCustomSaved) ;
    }

    @Override
    public UserCustomDTO findById(Long id) {
       if(!userCustomRespository.existsById(id)) {
           throw new BusinessException("Erro: " + id + " não encontrado");
       }else{
           return UserCustomMapper.INSTANCE.toDto(userCustomRespository.getReferenceById(id));
       }
    }


    private static void validatedNullObjeto(UserCustomDTO entityBody) {
        if( entityBody == null) throw new BusinessException("Erro: Objeto nulo");
    }
    private static void validatedObjectWithOutEmail(UserCustomDTO entityBody) {
        if( entityBody == null) throw new BusinessException("Erro: Erro : email null");
    }

    private void validatedEmailDuplicated(UserCustomDTO entityBody) {
        if(userCustomRespository.findByEmail(entityBody.getEmail())) throw new BusinessException("Erro : email ja cadastrado");
    }

    private void validatedObject(UserCustomDTO entityBody){
        log.info("------- Inicio validacao de novo auth ------");
        validatedNullObjeto(entityBody);
        validatedObjectWithOutEmail(entityBody);
        validatedEmailDuplicated(entityBody);
        log.info("------- Fim validacao de novo auth ------");
    }



}
