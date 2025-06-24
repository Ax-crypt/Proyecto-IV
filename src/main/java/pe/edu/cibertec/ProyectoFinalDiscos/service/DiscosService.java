package pe.edu.cibertec.ProyectoFinalDiscos.service;

import pe.edu.cibertec.ProyectoFinalDiscos.dto.DiscoDto;
import pe.edu.cibertec.ProyectoFinalDiscos.dto.DiskDto;


import java.util.List;
import java.util.Optional;

public interface DiscosService {
    List<DiscoDto> getAllDiscos() throws Exception;
    Optional<DiscoDto> getDiscoById(int id) throws Exception;
    boolean updateDisco(DiscoDto discoDto) throws Exception;
    boolean deleteDiscoById(int id) throws Exception;
    boolean addDisco(DiskDto discoDto) throws Exception;
}
