package com.example.demo.mapper;

import com.example.demo.builder.UserCustomBuilderTest;
import com.example.demo.dominio.UserCustom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;

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
    @Test
    public void testListUserDTOParseUSer(){
        var entities =  UserCustomMapper.INSTANCE.toEntites(builderUser.returnListUserDtoOkAtivo());

        Assertions.assertTrue(entities instanceof List<UserCustom>);
        Assertions.assertTrue(entities.get(0).getFirstName().equals("JOSEdto"));
    }
    @Test
    public void testListUserParseUserDto(){
        var dtos =  UserCustomMapper.INSTANCE.toDtos(builderUser.returnListUserOkAtivo());

        Assertions.assertTrue(dtos instanceof Collection<?>);
        Assertions.assertTrue(dtos.get(0).getFirstName().equals("JOSE"));
    }


}
