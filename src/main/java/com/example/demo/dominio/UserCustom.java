package com.example.demo.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserCustom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;
    private String firstName;
    private String email;
    private String fullName;
    private String password;

    private boolean account_non_expored = Boolean.TRUE;
    private boolean account_non_locked = Boolean.TRUE;
    private boolean account_non_expired = Boolean.TRUE;


    @Column(nullable = false)
    private Boolean enabled= Boolean.TRUE;
    @CreatedDate
    private Date createdAT;



}
