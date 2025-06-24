package pe.edu.cibertec.ProyectoFinalDiscos.dto;

import java.util.Date;

public record BuysDto(Integer id,
                      UserDto user,
                      Date fecha,
                      Double total
                      ) {
}
