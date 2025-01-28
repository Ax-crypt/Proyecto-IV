package pe.edu.cibertec.ProyectoFinalAplWeb1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.ProyectoFinalAplWeb1.dto.UserCreateDto;
import pe.edu.cibertec.ProyectoFinalAplWeb1.dto.UserDetailDto;
import pe.edu.cibertec.ProyectoFinalAplWeb1.dto.UserDto;
import pe.edu.cibertec.ProyectoFinalAplWeb1.entity.User;
import pe.edu.cibertec.ProyectoFinalAplWeb1.repository.UserRepository;
import pe.edu.cibertec.ProyectoFinalAplWeb1.service.ManageService;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() throws Exception {

        List<UserDto> users = new ArrayList<UserDto>();
        Iterable<User> iterable = userRepository.findAll();
        iterable.forEach(user -> users.add(new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getTelephone(),
                user.getAddress())));
        return users;

    }

    @Override
    public Optional<UserDetailDto> getUserById(int id) throws Exception {

        Optional<User> optional = userRepository.findById(id);
        return optional.map(user -> new UserDetailDto(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getTelephone(),
                user.getAddress(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()));

    }

    @Override
    public boolean updateUser(UserDetailDto userDto) throws Exception {

        Optional<User> optional = userRepository.findById(userDto.id());
        return optional.map(user -> {
            user.setFirstName(userDto.firstName());
            user.setLastName(userDto.lastName());
            user.setEmail(userDto.email());
            user.setTelephone(userDto.telephone());
            user.setAddress(userDto.address());
            user.setUpdatedAt(new Date());
            userRepository.save(user);
            return true;
        }).orElse(false);

    }

    @Override
    public boolean deleteUserById(int id) throws Exception {

        Optional<User> optional = userRepository.findById(id);
        return optional.map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);

    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean addUser(UserCreateDto userCreateDto) throws Exception {
        // Validar que el username o email no exista en la base de datos
        Optional<User> userByUsername = userRepository.findByUsername(userCreateDto.username());
        Optional<User> userByEmail = userRepository.findByEmail(userCreateDto.email());

        // Si el username o el email ya existen, no agregamos el nuevo usuario
        if (userByUsername.isPresent() || userByEmail.isPresent()) {
            return false;  // Ya existe un usuario con ese username o email
        } else {
            // Crear el nuevo objeto User
            User user = new User();
            user.setUsername(userCreateDto.username());
            // Hashear la contraseña antes de guardarla
            String hashedPassword = passwordEncoder.encode(userCreateDto.password());
            user.setPassword(hashedPassword);  // Establecer la contraseña hasheada
            user.setEmail(userCreateDto.email());
            user.setFirstName(userCreateDto.firstName());
            user.setLastName(userCreateDto.lastName());
            user.setTelephone(userCreateDto.telephone());
            user.setAddress(userCreateDto.address());
            user.setRole(userCreateDto.role());
            user.setCreatedAt(new Date());

            // Guardar el usuario en la base de datos
            userRepository.save(user);
            return true;  // Usuario agregado
        }
    }


}
