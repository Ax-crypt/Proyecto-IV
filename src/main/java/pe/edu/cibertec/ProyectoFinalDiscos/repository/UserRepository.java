package pe.edu.cibertec.ProyectoFinalDiscos.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.cibertec.ProyectoFinalDiscos.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
