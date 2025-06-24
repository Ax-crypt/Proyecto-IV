package pe.edu.cibertec.ProyectoFinalDiscos.response;

import pe.edu.cibertec.ProyectoFinalDiscos.dto.DiscoDto;

public record FindDiscosResponse(String code,
                                 String message,
                                 Iterable<DiscoDto> discoDto) {
}
