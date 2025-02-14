package pe.edu.cibertec.ProyectoFinalAplWeb1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.ProyectoFinalAplWeb1.entity.Buys;
import pe.edu.cibertec.ProyectoFinalAplWeb1.entity.User;

import java.util.List;

public interface BuysRepository extends JpaRepository<Buys, Integer> {
    List<Buys> findByUser(User user);
}
