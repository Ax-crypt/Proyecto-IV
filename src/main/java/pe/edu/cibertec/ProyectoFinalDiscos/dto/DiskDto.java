package pe.edu.cibertec.ProyectoFinalDiscos.dto;

import pe.edu.cibertec.ProyectoFinalDiscos.entity.Genero;

public record DiskDto(String artista,
                      String discografica,
                      String anio,
                      Double precio,
                      Genero genero,
                      String nombre) {
}
