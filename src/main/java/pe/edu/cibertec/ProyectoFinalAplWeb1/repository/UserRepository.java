package pe.edu.cibertec.ProyectoFinalAplWeb1.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.cibertec.ProyectoFinalAplWeb1.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

}
