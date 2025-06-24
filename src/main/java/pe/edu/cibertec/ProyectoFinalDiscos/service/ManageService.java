package pe.edu.cibertec.ProyectoFinalDiscos.service;

import pe.edu.cibertec.ProyectoFinalDiscos.dto.UserCreateDto;
import pe.edu.cibertec.ProyectoFinalDiscos.dto.UserDetailDto;
import pe.edu.cibertec.ProyectoFinalDiscos.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface ManageService {
    List<UserDto> getAllUsers() throws Exception;
    Optional<UserDetailDto> getUserById(int id) throws Exception;
    boolean updateUser(UserDetailDto userDto) throws Exception;
    boolean addUser(UserCreateDto userCreateDto) throws Exception;
    boolean deleteUserById(int id) throws Exception;
    Optional<UserDetailDto> getUserByUsername(String username) throws Exception;
}
