package pe.edu.cibertec.ProyectoFinalAplWeb1.response;

import pe.edu.cibertec.ProyectoFinalAplWeb1.dto.DiscoDto;

public record FindDiscosResponse(String code,
                                 String message,
                                 Iterable<DiscoDto> discoDto) {
}
