package pe.edu.cibertec.ProyectoFinalAplWeb1.service;

import pe.edu.cibertec.ProyectoFinalAplWeb1.dto.UserCreateDto;
import pe.edu.cibertec.ProyectoFinalAplWeb1.dto.UserDetailDto;
import pe.edu.cibertec.ProyectoFinalAplWeb1.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface ManageService {
    List<UserDto> getAllUsers() throws Exception;
    Optional<UserDetailDto> getUserById(int id) throws Exception;
    boolean updateUser(UserDetailDto userDto) throws Exception;
    boolean addUser(UserCreateDto userCreateDto) throws Exception;
    boolean deleteUserById(int id) throws Exception;
}
