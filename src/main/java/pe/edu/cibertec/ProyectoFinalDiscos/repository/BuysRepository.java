package pe.edu.cibertec.ProyectoFinalDiscos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.ProyectoFinalDiscos.entity.Buys;
import pe.edu.cibertec.ProyectoFinalDiscos.entity.User;

import java.util.List;

public interface BuysRepository extends JpaRepository<Buys, Integer> {
    List<Buys> findByUser(User user);
}
