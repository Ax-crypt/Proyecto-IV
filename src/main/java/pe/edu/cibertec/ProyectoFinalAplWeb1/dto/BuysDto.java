package pe.edu.cibertec.ProyectoFinalAplWeb1.dto;

import java.util.Date;

public record BuysDto(Integer id,
                      UserDto user,
                      Date fecha,
                      Double total
                      ) {
}
