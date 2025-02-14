package pe.edu.cibertec.ProyectoFinalAplWeb1.dto;

import java.util.Date;

public record NewBuyDto(UserDto user,
                        Date fecha,
                        Double total) {
}
