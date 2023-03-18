package com.example.demo.dominio;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.Temporal;

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
    private Long idUser;
    private String firstName;
    private String fullName;
    private String password;

    @Column(nullable = false)
    private Boolean enabled;
    @CreatedDate
    private Date createdAT;



}
