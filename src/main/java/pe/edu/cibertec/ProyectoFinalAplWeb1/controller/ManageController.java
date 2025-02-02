package pe.edu.cibertec.ProyectoFinalAplWeb1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.cibertec.ProyectoFinalAplWeb1.dto.DiscoDto;
import pe.edu.cibertec.ProyectoFinalAplWeb1.dto.UserCreateDto;
import pe.edu.cibertec.ProyectoFinalAplWeb1.dto.UserDetailDto;
import pe.edu.cibertec.ProyectoFinalAplWeb1.dto.UserDto;
import pe.edu.cibertec.ProyectoFinalAplWeb1.service.DiscosService;
import pe.edu.cibertec.ProyectoFinalAplWeb1.service.ManageService;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/manage")
public class ManageController {
    
    @Autowired
    ManageService manageService;

    @GetMapping("/home")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/restricted")
    public String restricted(Model model) {
        return "restricted";
    }

    @GetMapping("/start")
    public String start(Model model) {

        try {
            List<UserDto> users = manageService.getAllUsers();
            model.addAttribute("users", users);
            model.addAttribute("error", null);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ocurrió un error al obtener los datos");

        }
        return "manage";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        try {
            // Obtener detalles del usuario por ID usando Optional
            Optional<UserDetailDto> userDetailOptional = manageService.getUserById(id);

            // Verificar si el Optional tiene un valor (isPresent)
            if (userDetailOptional.isPresent()) {
                // Agregar detalles del usuario al modelo si está presente
                model.addAttribute("userDetail", userDetailOptional.get());
            } else {
                // Manejar caso cuando no se encuentra el usuario
                model.addAttribute("error", "No se encontró el usuario con ID: " + id);
                return "error"; // Vista de error personalizada
            }
        } catch (Exception e) {
            // Manejar cualquier excepción y registrar el error
            e.printStackTrace();
            model.addAttribute("error", "Ocurrió un error al obtener los datos del usuario.");
            return "error"; // Vista de error personalizada
        }

        // Retornar la vista de detalles del usuario
        return "manage_detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
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
        return "manage_edit";  // Asegúrate de tener la vista manage_edit.html
    }

    @PostMapping("/edit-confirm")
    public String editConfirm(@ModelAttribute UserDetailDto userDetailDto, RedirectAttributes redirectAttributes) {
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
        return "redirect:/manage/start";  // Redirige al inicio después de actualizar
    }

    // Nuevo método para mostrar el formulario para agregar un nuevo usuario
    @GetMapping("/add")
    public String add(Model model) {
        // Establecer valores predeterminados para los campos en lugar de null
        model.addAttribute("userDetailDto", new UserCreateDto(
                "",
                "",
                "",
                "",
                "",
                0,
                "",
                "",
                new Date(),
                new Date()
        ));
        return "manage-add";
    }

    // Método para manejar el envío del formulario y agregar el nuevo usuario
    @PostMapping("/add-confirm")
    public String addConfirm(@ModelAttribute UserCreateDto userCreateDto, Model model, RedirectAttributes redirectAttributes) throws Exception {
        try {
            // Llamar al servicio para agregar el nuevo usuario
            boolean success = manageService.addUser(userCreateDto);
            if (success) {
                redirectAttributes.addFlashAttribute("success", "Usuario agregado exitosamente.");
                return "redirect:/manage/start";  // Redirige al inicio después de guardar
            } else {
                model.addAttribute("error", "Hubo un problema al agregar el usuario.");
                return "manage-add";  // Vuelve al formulario si hubo un error
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ocurrió un error al agregar el usuario.");
            return "manage-add";  // Vuelve al formulario si hubo un error
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) throws Exception {
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
        return "redirect:/manage/start";  // Redirige al inicio después de eliminar
    }


    /* /*++++++++++++++++++++++++++++++++++++++++++++/ /*+++++++++++++++++++++++++++++++++++++++++++++++++++/
                                /*                                   /*
                                /* metodos para la tienda de discos (usuario) /*
                                /*                                     */
    /*++++++++++++++++++++++++++++++++++++++++++++++/  /*+++++++++++++++++++++++++++++++++++++++++++++++++++/ */
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



}
