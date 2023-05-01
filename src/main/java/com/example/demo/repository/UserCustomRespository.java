package com.example.demo.repository;

import com.example.demo.dominio.UserCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCustomRespository extends JpaRepository<UserCustom,Long> {

   UserCustom findUserCustomByEmail( String email);


}
