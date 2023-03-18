package com.example.demo.controller;

import com.example.demo.service.Dto.UserCustomDTO;
import com.example.demo.service.UserCustomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/auth")
public class UserCustomController {
    @Autowired
    private UserCustomService userCustomService;
    Logger log = LoggerFactory.getLogger(UserCustomController.class);



    @PostMapping
    public ResponseEntity<UserCustomDTO> createUser(@RequestBody UserCustomDTO eBody){
        log.info("Chamada de criacao user");
        return ResponseEntity.ok(userCustomService.save(eBody));

    }

}
