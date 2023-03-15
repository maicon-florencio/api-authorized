package com.example.demo.mapper;

import com.example.demo.dominio.UserCustom;
import com.example.demo.service.DTO.UserCustomDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring" ,uses = {})
public interface UserCustomMapper {


    UserCustomMapper INSTANCE = Mappers.getMapper(UserCustomMapper.class);
    @Mapping(source = "entity.fullName" , target = "fullName")
    UserCustomDTO toDto (UserCustom entity);

    @InheritInverseConfiguration
    UserCustom toEntity (UserCustomDTO dto);


    List<UserCustomDTO> toDtos (List<UserCustom> entities);
    List<UserCustom> toEntites (List<UserCustomDTO> dtos);





}
