package pe.edu.cibertec.ProyectoFinalDiscos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.ProyectoFinalDiscos.entity.Genero;

public interface GenderRepository extends JpaRepository<Genero, Integer> {
}
