package pe.edu.cibertec.ProyectoFinalAplWeb1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genero {
    @Id
    private Integer idGenero;
    private String nombre;
    @OneToMany
    private List<Disco> discos;
}
