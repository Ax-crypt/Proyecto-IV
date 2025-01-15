package pe.edu.cibertec.ProyectoFinalAplWeb1.dto;

import java.util.Date;

public record UserCreateDto(String username,
                            String password,
                            String email,
                            String firstName,
                            String lastName,
                            Integer telephone,
                            String address,
                            String role,
                            Date createdAt,
                            Date updatedAt) {
}
