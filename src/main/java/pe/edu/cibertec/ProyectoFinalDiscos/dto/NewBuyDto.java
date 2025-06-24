package pe.edu.cibertec.ProyectoFinalDiscos.dto;

import java.util.Date;

public record NewBuyDto(UserDto user,
                        Date fecha,
                        Double total) {
}
