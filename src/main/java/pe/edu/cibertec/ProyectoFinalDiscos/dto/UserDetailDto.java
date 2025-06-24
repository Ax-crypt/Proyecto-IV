package pe.edu.cibertec.ProyectoFinalDiscos.dto;

import java.util.Date;

public record UserDetailDto(Integer id,
                            String username,
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
