package pe.edu.cibertec.ProyectoFinalDiscos.dto;

import pe.edu.cibertec.ProyectoFinalDiscos.entity.Genero;

public record DiscoDto(Integer id,
                       String artista,
                       String discografica,
                       String anio,
                       Double precio,
                       Genero genero,
                       String nombre) {
}
