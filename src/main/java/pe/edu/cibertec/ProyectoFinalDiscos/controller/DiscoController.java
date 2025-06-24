package pe.edu.cibertec.ProyectoFinalDiscos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.cibertec.ProyectoFinalDiscos.dto.*;
import pe.edu.cibertec.ProyectoFinalDiscos.entity.Genero;
import pe.edu.cibertec.ProyectoFinalDiscos.repository.GenderRepository;
import pe.edu.cibertec.ProyectoFinalDiscos.service.BuysService;
import pe.edu.cibertec.ProyectoFinalDiscos.service.DiscosService;
import pe.edu.cibertec.ProyectoFinalDiscos.service.ManageService;
import pe.edu.cibertec.ProyectoFinalDiscos.service.impl.CarService;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/disk")
public class DiscoController {
    @Autowired
    private DiscosService discosService;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private BuysService buysService;

    @Autowired
    private ManageService manageService;

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

    // Mostrar lista de discos
    @GetMapping("/discos")
    public String lisDiscos(Model model) {
        try {
            List<DiscoDto> discos = discosService.getAllDiscos();
            model.addAttribute("discos", discos);
            model.addAttribute("cantidadCarrito", carService.getCantidadTotal());
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


    // Método para carrito
    @PostMapping("/agregar/{id}")
    public String agregarDiscoAlCarrito(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            Optional<DiscoDto> disco = discosService.getDiscoById(id);
            if (disco.isPresent()) {
                carService.agregarDisco(disco.get());
                redirectAttributes.addFlashAttribute("mensaje", "Disco agregado al carrito");
            } else {
                redirectAttributes.addFlashAttribute("error", "Disco no encontrado");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error al agregar el disco al carrito");
        }
        return "redirect:/disk/discos";
    }

    @GetMapping("/ver")
    public String verCarrito(Model model) {
        model.addAttribute("items", carService.getItems());
        model.addAttribute("cantidadTotal", carService.getCantidadTotal());
        model.addAttribute("totalPagar", carService.getTotalPagar());
        return "car";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarDiscoDelCarrito(@PathVariable int id, RedirectAttributes redirectAttributes) {
        carService.eliminarDisco(id);
        redirectAttributes.addFlashAttribute("mensaje", "Disco eliminado del carrito");
        return "redirect:/disk/ver";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarCantidad(@PathVariable int id, @RequestParam int cantidad, RedirectAttributes redirectAttributes) {
        carService.actualizarCantidad(id, cantidad);
        redirectAttributes.addFlashAttribute("mensaje", "Cantidad actualizada");
        return "redirect:/disk/ver";
    }

    @PostMapping("/vaciar")
    public String vaciarCarrito(RedirectAttributes redirectAttributes) {
        carService.vaciarCarrito();
        redirectAttributes.addFlashAttribute("mensaje", "Carrito vaciado");
        return "redirect:/disk/ver";
    }

    @GetMapping("/payment")
    public String mostrarFormularioPago() {
        return "pago";
    }

    @PostMapping("/payment/process")
    public String procesarPago(Principal principal, RedirectAttributes redirectAttributes) {
        try {
            String username = principal.getName();
            Optional<UserDetailDto> userOptional = manageService.getUserByUsername(username);

            if (userOptional.isPresent()) {
                UserDetailDto userDetail = userOptional.get();
                UserDto user = new UserDto(
                        userDetail.id(),
                        userDetail.firstName(),
                        userDetail.lastName(),
                        userDetail.email(),
                        userDetail.telephone(),
                        userDetail.address()
                );

                double totalPagar = carService.getTotalPagar();

                // Guardar la compra usando DTO
                BuysDto nuevaCompra = new BuysDto(null, user, new Date(), totalPagar);

                buysService.save(nuevaCompra); // Este método debe guardar la compra en la base de datos

                // Vaciar el carrito
                carService.vaciarCarrito();

                // Mensaje con Alertify
                redirectAttributes.addFlashAttribute("compraExitosa", true);
                return "redirect:/disk/discos";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al procesar el pago");
        }
        return "redirect:/disk/ver";
    }

}

