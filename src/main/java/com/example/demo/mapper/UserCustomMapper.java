package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring" ,uses = {})
public class UserCustomMapper {


    UserCustomMapper INSTANCE = Mappers.getMapper(UserCustomMapper.class);




}
