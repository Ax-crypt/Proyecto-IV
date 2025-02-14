package pe.edu.cibertec.ProyectoFinalAplWeb1.service;

import pe.edu.cibertec.ProyectoFinalAplWeb1.dto.BuysDto;
import pe.edu.cibertec.ProyectoFinalAplWeb1.dto.UserDetailDto;

import java.util.List;

public interface BuysService {
    List<BuysDto> findByUser(UserDetailDto user) throws Exception;
    void save(BuysDto buysDto) throws Exception;
}
