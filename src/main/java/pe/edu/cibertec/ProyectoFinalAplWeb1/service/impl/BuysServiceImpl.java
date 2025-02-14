package pe.edu.cibertec.ProyectoFinalAplWeb1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.ProyectoFinalAplWeb1.dto.BuysDto;
import pe.edu.cibertec.ProyectoFinalAplWeb1.dto.UserDetailDto;
import pe.edu.cibertec.ProyectoFinalAplWeb1.dto.UserDto;
import pe.edu.cibertec.ProyectoFinalAplWeb1.entity.Buys;
import pe.edu.cibertec.ProyectoFinalAplWeb1.entity.User;
import pe.edu.cibertec.ProyectoFinalAplWeb1.repository.BuysRepository;
import pe.edu.cibertec.ProyectoFinalAplWeb1.repository.UserRepository;
import pe.edu.cibertec.ProyectoFinalAplWeb1.service.BuysService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BuysServiceImpl implements BuysService {
    @Autowired
    private BuysRepository buysRepository; //  BuysRepository

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<BuysDto> findByUser(UserDetailDto userDto) throws Exception {
        try {
            // Convertir UserDto a la entidad User
            Optional<User> userOptional = userRepository.findById(userDto.id());
            if (userOptional.isEmpty()) {
                throw new Exception("Usuario no encontrado.");
            }

            User user = userOptional.get();

            // Obtener las compras del usuario
            List<Buys> compras = buysRepository.findByUser(user); // Cambiar buysService por buysRepository

            // Convertir las entidades Buys a DTOs BuysDto
            return compras.stream()
                    .map(compra -> new BuysDto(
                            compra.getId(),
                            new UserDto(
                                    user.getId(),
                                    user.getFirstName(),
                                    user.getLastName(),
                                    user.getEmail(),
                                    user.getTelephone(),
                                    user.getAddress()
                            ),
                            compra.getFecha(),
                            compra.getTotal()
                    ))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("Error al obtener las compras del usuario: " + e.getMessage());
        }
    }

    @Override
    public void save(BuysDto buysDto) throws Exception {
        try {
            Optional<User> userOptional = userRepository.findById(buysDto.user().id());
            if (userOptional.isEmpty()) {
                throw new Exception("Usuario no encontrado.");
            }

            User user = userOptional.get();
            Buys compra = new Buys();
            compra.setUser(user);
            compra.setFecha(buysDto.fecha());
            compra.setTotal(buysDto.total());

            buysRepository.save(compra);
        } catch (Exception e) {
            throw new Exception("Error al guardar la compra: " + e.getMessage());
        }
    }


}
