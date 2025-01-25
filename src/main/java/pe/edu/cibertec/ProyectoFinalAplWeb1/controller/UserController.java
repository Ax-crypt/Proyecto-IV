package pe.edu.cibertec.ProyectoFinalAplWeb1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.cibertec.ProyectoFinalAplWeb1.dto.UserCreateDto;
import pe.edu.cibertec.ProyectoFinalAplWeb1.dto.UserDetailDto;
import pe.edu.cibertec.ProyectoFinalAplWeb1.service.ManageService;

import java.util.Date;
import java.util.Optional;

@Controller()
public class UserController {

    @Autowired
    ManageService manageService;

    @GetMapping("/account")
    public String MyAccount(Model model) {return "myAccount";}

    // Nuevo método para mostrar el formulario para agregar un nuevo usuario
    @GetMapping("/register")
    public String register(Model model) {
        // Establecer valores predeterminados para los campos en lugar de null
        model.addAttribute("user", new UserCreateDto(
                "",
                "",
                "",
                "",
                "",
                9,
                "",
                "USER",
                new Date(),
                new Date()
        ));
        return "registrarUser";
    }


    // Método para manejar el envío del formulario y agregar el nuevo usuario
    @PostMapping("/register-confirm")
    public String registerConfirm(@ModelAttribute UserCreateDto userCreateDto, Model model, RedirectAttributes redirectAttributes) throws Exception {
        try {
            // Llamar al servicio para agregar el nuevo usuario
            boolean success = manageService.addUser(userCreateDto);
            if (success) {
                redirectAttributes.addFlashAttribute("success", "Usuario agregado exitosamente.");
                return "redirect:/manage/home";  // Redirige al inicio después de guardar
            } else {
                model.addAttribute("error", "Hubo un problema al agregar el usuario.");
                return "registrarUser";  // Vuelve al formulario si hubo un error
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ocurrió un error al agregar el usuario.");
            return "registrarUser";  // Vuelve al formulario si hubo un error
        }
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model) {
        try {
            // Obtener detalles del usuario por ID, utilizando Optional
            Optional<UserDetailDto> userDetailDtoOpt = manageService.getUserById(id);

            if (userDetailDtoOpt.isPresent()) {
                // Si el usuario se encuentra, lo añadimos al modelo
                model.addAttribute("userDetailDto", userDetailDtoOpt.get());
            } else {
                // Si no se encuentra el usuario, agregar mensaje de error
                model.addAttribute("error", "Usuario no encontrado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ocurrió un error al obtener los detalles del usuario.");
        }
        return "storeUpdateUser";
    }

    @PostMapping("/update-confirm")
    public String updateConfirm(@ModelAttribute UserDetailDto userDetailDto, RedirectAttributes redirectAttributes) {
        try {
            // Llamar al servicio para actualizar el usuario
            boolean success = manageService.updateUser(userDetailDto);
            if (success) {
                redirectAttributes.addFlashAttribute("success", "Usuario actualizado exitosamente.");
            } else {
                redirectAttributes.addFlashAttribute("error", "No se pudo actualizar el usuario.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error al actualizar el usuario.");
        }
        return "redirect:/manage/account";  // Redirige después de actualizar
    }

    @GetMapping("/del/{id}")
    public String deleteAccount(@PathVariable Integer id, RedirectAttributes redirectAttributes) throws Exception {
        try {
            // Llamar al servicio para eliminar al usuario por ID
            boolean success = manageService.deleteUserById(id);
            if (success) {
                redirectAttributes.addFlashAttribute("success", "Usuario eliminado correctamente.");
            } else {
                redirectAttributes.addFlashAttribute("error", "Hubo un problema al eliminar el usuario.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error al eliminar el usuario.");
        }
        return "redirect:/manage/home";  // Redirige al inicio después de eliminar
    }
}
