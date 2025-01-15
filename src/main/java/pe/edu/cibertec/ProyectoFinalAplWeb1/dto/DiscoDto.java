package pe.edu.cibertec.ProyectoFinalAplWeb1.dto;

import pe.edu.cibertec.ProyectoFinalAplWeb1.entity.Genero;

public record DiscoDto(Integer id,
                       String artista,
                       String discografica,
                       String anio,
                       Double precio,
                       Genero genero,
                       String nombre) {
}
