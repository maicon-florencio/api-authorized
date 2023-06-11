package com.example.demo.controller;

import com.example.demo.service.Dto.UserCustomDTO;
import com.example.demo.service.UserCustomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/user")
@Tag(name="People", description = " Endpoints relationship People")
public class UserCustomController {
    @Autowired
    private UserCustomService userCustomService;
    Logger log = LoggerFactory.getLogger(UserCustomController.class);

    @PostMapping
    public ResponseEntity<UserCustomDTO> createUser(@RequestBody UserCustomDTO eBody){
        log.info("Chamada de criacao user");
        return ResponseEntity.ok(userCustomService.save(eBody));

    }
    @GetMapping("/{id}")
    @Operation(summary = "Finds person from ID", description = "Finds person from ID", responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
                    mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = UserCustomDTO.class))
            )),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<UserCustomDTO> findById(@PathVariable("id") Long id){
        log.info("Chamada de buscar user por ID");
        return ResponseEntity.ok(userCustomService.findById(id));
    }

    @GetMapping("/find-user-by-email/{email}")
    public ResponseEntity<UserCustomDTO> findById(@PathVariable("email") String email){
        log.info("Chamada de buscar user por Email");
        return ResponseEntity.ok(userCustomService.findUserByEmail(email));
    }

}
