package com.example.demo.controllerTest;

import com.example.demo.builder.UserCustomBuilderTest;
import com.example.demo.exception.BusinessException;
import com.example.demo.service.UserCustomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class UserCustomControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected UserCustomService userCustomService;

    @Autowired
    protected UserCustomBuilderTest userBuilder;

    private static final String API_URL = "/v1/api/auth";
    @Test
    void createdNewUserCustom() throws Exception{

        var userDto = userBuilder.returnUserDTOOKAtivo();

        Mockito.when(userCustomService.save(Mockito.any())).thenReturn(userDto) ;

        var request = MockMvcRequestBuilders.post(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(userDto))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("idUser").isNotEmpty())
                .andExpect(jsonPath("email").value("teste@gmail.com"));

        Assertions.assertEquals("teste@gmail.com", userDto.getEmail());
        Assertions.assertEquals(3L, (long) userDto.getIdUser());

    }

    @Test
     void errorCreatedNewUserCustomWithOutEmail() throws Exception{

        var userDto = userBuilder.returnUserDTOOKAtivo();
        userDto.setEmail("");

        Mockito.when(userCustomService.save(userDto)).thenThrow(new BusinessException("Erro : email null")) ;

        var request = MockMvcRequestBuilders.post(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(userDto))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest());

    }
    @Test
     void findUserCustomById() throws Exception {

        var userDto = userBuilder.returnUserDTOOKAtivo();

        Mockito.when(userCustomService.findById(3L)).thenReturn(userDto) ;

        var request = MockMvcRequestBuilders.get(API_URL.concat("/3"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("idUser").isNotEmpty())
                .andExpect(jsonPath("email").value("teste@gmail.com"));

        Assertions.assertEquals("teste@gmail.com", userDto.getEmail());
        Assertions.assertEquals(3L, (long) userDto.getIdUser());

    }


}
