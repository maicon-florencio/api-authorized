package com.example.demo.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permissao")
    private Long idPermissao;
    private String description;
    @ManyToMany
    @JoinTable(name="pessoa_has_permission", joinColumns=
            {@JoinColumn(name="id_user")}, inverseJoinColumns=
            {@JoinColumn(name="id_permissao")})
    private List<UserCustom> users;

}
