package pe.edu.cibertec.ProyectoFinalAplWeb1.response;

import pe.edu.cibertec.ProyectoFinalAplWeb1.dto.DiskDto;

public record AddDiscosResponse(String code,
                                String message,
                                Iterable<DiskDto> discoDto) {
}
