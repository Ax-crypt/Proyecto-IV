package pe.edu.cibertec.ProyectoFinalDiscos.response;

import pe.edu.cibertec.ProyectoFinalDiscos.dto.DiskDto;

public record AddDiscosResponse(String code,
                                String message,
                                Iterable<DiskDto> discoDto) {
}
