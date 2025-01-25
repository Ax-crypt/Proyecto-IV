package pe.edu.cibertec.ProyectoFinalAplWeb1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.ProyectoFinalAplWeb1.dto.DiscoDto;
import pe.edu.cibertec.ProyectoFinalAplWeb1.dto.DiskDto;
import pe.edu.cibertec.ProyectoFinalAplWeb1.entity.Genero;
import pe.edu.cibertec.ProyectoFinalAplWeb1.repository.GenderRepository;
import pe.edu.cibertec.ProyectoFinalAplWeb1.service.DiscosService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/disk")
public class DiscoController {
    @Autowired
    private DiscosService discosService;

    @Autowired
    private GenderRepository genderRepository;

    // Método para listar todos los discos
    @GetMapping
    public String listarDiscos(Model model) {
        try {
            List<DiscoDto> discos = discosService.getAllDiscos();
            model.addAttribute("discos", discos);
            return "listarDiscos";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al obtener la lista de discos.");
            return "error";
        }
    }

    // Método para ver el detalle de un disco
    @GetMapping("/{id}")
    public String detalleDisco(@PathVariable("id") int id, Model model) {
        try {
            Optional<DiscoDto> disco = discosService.getDiscoById(id);
            if (disco.isPresent()) {
                model.addAttribute("disco", disco.get());
                return "detalleDisco";
            } else {
                model.addAttribute("error", "Disco no encontrado.");
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al obtener el detalle del disco.");
            return "error";
        }
    }


    // Método para mostrar el formulario de agregar disco
    @GetMapping("/add")
    public String mostrarAgregar(Model model) {
        List<Genero> generos = genderRepository.findAll();
        model.addAttribute("generos", generos);
        model.addAttribute("disco", new DiskDto("", "", "", 0.0, null, ""));
        return "agregarDisco";
    }

    // Método para manejar el envío del formulario de agregar disco
    @PostMapping("/add")
    public String agregarDisco(@ModelAttribute DiskDto discoDto, Model model) {
        try {
            boolean resultado = discosService.addDisco(discoDto);
            if (resultado) {
                return "redirect:/disk";
            } else {
                model.addAttribute("error", "Error al agregar el disco.");
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al agregar el disco.");
            return "error";
        }
    }

    // Método para mostrar el formulario de editar disco
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") int id, Model model) {
        try {
            Optional<DiscoDto> disco = discosService.getDiscoById(id);
            List<Genero> generos = genderRepository.findAll();
            if (disco.isPresent()) {
                model.addAttribute("disco", disco.get());
                model.addAttribute("generos", generos);
                return "editarDisco";
            } else {
                model.addAttribute("error", "Disco no encontrado.");
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al obtener el detalle del disco.");
            return "error";
        }
    }

    // Método para manejar el envío del formulario de editar disco
    @PostMapping("/editar/{id}")
    public String editarDisco(@PathVariable("id") int id, @ModelAttribute DiscoDto discoDto, Model model) {
        try {
            boolean resultado = discosService.updateDisco(discoDto);
            if (resultado) {
                return "redirect:/disk";
            } else {
                model.addAttribute("error", "Error al actualizar el disco.");
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al actualizar el disco.");
            return "error";
        }
    }


    // Método para eliminar un disco
    @GetMapping("/delete/{id}")
    public String eliminarDisco(@PathVariable("id") int id, Model model) {
        try {
            boolean resultado = discosService.deleteDiscoById(id);
            if (resultado) {
                return "redirect:/disk";
            } else {
                model.addAttribute("error", "Error al eliminar el disco.");
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al eliminar el disco.");
            return "error";
        }
    }


   /* /*++++++++++++++++++++++++++++++++++++++++++++/ /*+++++++++++++++++++++++++++++++++++++++++++++++++++/
                                /*                                   /*
                                /* metodos para la tienda de discos  /*
                                /*                                     */
    /*++++++++++++++++++++++++++++++++++++++++++++++/  /*+++++++++++++++++++++++++++++++++++++++++++++++++++/ */

    @GetMapping("/account")
    public String MyAccount(Model model) {return "myAccount";}

    // Mostrar lista de discos
    @GetMapping("/discos")
    public String lisDiscos(Model model) {
        try {
            List<DiscoDto> discos = discosService.getAllDiscos();
            model.addAttribute("discos", discos);
            return "storeDiscos";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al obtener la lista de discos.");
            return "error";
        }
    }


    // Método para ver el detalle de un disco
    @GetMapping("/detail/{id}")
    public String detalle(@PathVariable("id") int id, Model model) {
        try {
            Optional<DiscoDto> disco = discosService.getDiscoById(id);
            if (disco.isPresent()) {
                model.addAttribute("disco", disco.get());
                return "storeDetailsDisk";
            } else {
                model.addAttribute("error", "Disco no encontrado.");
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al obtener el detalle del disco.");
            return "error";
        }
    }


}

