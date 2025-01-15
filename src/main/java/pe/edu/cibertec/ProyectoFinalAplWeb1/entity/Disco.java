package pe.edu.cibertec.ProyectoFinalAplWeb1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Disco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String artista;
    private String discografica;
    private String anio;
    private Double precio;
    @ManyToOne
    private Genero genero;
    private String nombre;

}
