package pe.edu.cibertec.ProyectoFinalDiscos.service;

import pe.edu.cibertec.ProyectoFinalDiscos.dto.BuysDto;
import pe.edu.cibertec.ProyectoFinalDiscos.dto.UserDetailDto;

import java.util.List;

public interface BuysService {
    List<BuysDto> findByUser(UserDetailDto user) throws Exception;
    void save(BuysDto buysDto) throws Exception;
}
