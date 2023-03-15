package com.example.demo.mapper;

import com.example.demo.builder.UserCustomBuilderTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserCustomMapperTest {
    @Autowired
    UserCustomBuilderTest builderUser;
    @Test
    public void testOkUserParseUserDTO(){

        var userDto = UserCustomMapper.INSTANCE.toDto(builderUser.returnUserOkAtivo());

        Assertions.assertEquals(userDto.getFirstName(),"JOSE");
        Assertions.assertEquals(userDto.getFullName(),"S Quieroz");
        Assertions.assertEquals(userDto.getPassword(),"banana");

    }

    @Test
    public void testOkUserDTOParseUser(){

        var user = UserCustomMapper.INSTANCE.toEntity(builderUser.returnUserDTOOKAtivo());

        Assertions.assertEquals(user.getFirstName(),"Martia");
        Assertions.assertEquals(user.getFullName(),"Sanchez");
        Assertions.assertEquals(user.getPassword(),"Cocacola");

    }


}
