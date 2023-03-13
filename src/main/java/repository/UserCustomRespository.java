package repository;

import dominio.UserCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCustomRespository extends JpaRepository<UserCustom,Long> {

}
