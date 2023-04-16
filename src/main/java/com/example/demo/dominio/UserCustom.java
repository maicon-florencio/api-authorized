package com.example.demo.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserCustom implements UserDetails,Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;
    @Column(name = "first_name")
    private String firstName;
    @Column(unique = true)
    private String email;
    @Column(name = "full_name")
    private String fullName;
    private String password;
    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired;
    @Column(name = "account_non_locked")
    private boolean accountNonLocked = Boolean.TRUE;
    @Column(name = "account_non_expired")
    private boolean accountNonExpired = Boolean.TRUE;
    @Column(nullable = false)
    private Boolean enabled= Boolean.TRUE;
    @CreatedDate
    private Date createdAT;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_has_permission",joinColumns = {@JoinColumn (name = "id_user")},
            inverseJoinColumns =  {@JoinColumn (name = "id_permissao")})
    private List<Permissao> permissoes;

    public List<String> getRoles(){
        List<String> roles =  new ArrayList<>();
        permissoes.forEach(p -> roles.add(p.getDescription()));
        return roles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissoes;
    }

    @Override
    public String getUsername() {
        return this.firstName;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }
    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
@Override
    public String getPassword() {
        return this.password;
    }
}
