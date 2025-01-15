package pe.edu.cibertec.ProyectoFinalAplWeb1.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.ProyectoFinalAplWeb1.dto.DiscoDto;
import pe.edu.cibertec.ProyectoFinalAplWeb1.dto.DiskDto;
import pe.edu.cibertec.ProyectoFinalAplWeb1.response.AddDiscosResponse;
import pe.edu.cibertec.ProyectoFinalAplWeb1.response.DeleteDiscosResponse;
import pe.edu.cibertec.ProyectoFinalAplWeb1.response.FindDiscosResponse;
import pe.edu.cibertec.ProyectoFinalAplWeb1.response.UpdateDiscosResponse;
import pe.edu.cibertec.ProyectoFinalAplWeb1.service.DiscosService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manage-discos")
public class DiscosApi {

    @Autowired
    DiscosService discosService;

    @GetMapping("/all")
public FindDiscosResponse findDiscos(@RequestParam(value = "id", defaultValue = "0") String id ) {
    try {
        if (Integer.parseInt(id) > 0) {
            Optional<DiscoDto> optional =   discosService.getDiscoById(Integer.parseInt(id));
            if (optional.isPresent()) {
                DiscoDto discoDto = optional.get();
                return new FindDiscosResponse("01", "List of discs", List.of(discoDto));
            } else {
                return new FindDiscosResponse("02", "discs not found", null);
            }

        } else {
            List<DiscoDto> discos = discosService.getAllDiscos();
            if (!discos.isEmpty())
                return new FindDiscosResponse("01", "List of discs", discos);
            else
                return new FindDiscosResponse("03", "discs list not found", discos);
        }
    }catch (Exception e){
        e.printStackTrace();
        return new FindDiscosResponse("99","Service not found",null);
    }
}

@GetMapping("/details-discos")
public FindDiscosResponse findDiscosById(@RequestParam(value = "id", defaultValue = "0") String id) {

    try {
        if (Integer.parseInt(id) > 0) {
            Optional<DiscoDto> optional = discosService.getDiscoById(Integer.parseInt(id));
                    if (optional.isPresent()) {
                        DiscoDto discoDto = optional.get();
                return new FindDiscosResponse("01", "Disk detail", List.of(discoDto));
            } else {
                return new FindDiscosResponse("02", "Disk not found", null);
            }

        } else
            return new FindDiscosResponse("03", "Parameter not found", null);

    } catch (Exception e) {
        e.printStackTrace();
        return new FindDiscosResponse("99", "Service not found", null);

    }
}

@PostMapping("/update-discos")
public UpdateDiscosResponse updateDisco(@RequestBody DiscoDto updateDisco) {
    try {
        if (discosService.updateDisco(updateDisco)) {
            return new UpdateDiscosResponse("01", "Successful update");
        } else {
            return new UpdateDiscosResponse("02", "Disk not found");
        }

    } catch (Exception e) {
        e.printStackTrace();
        return new UpdateDiscosResponse("99", "Service not found");

    }

}
@PostMapping("/add-discos")
public AddDiscosResponse addDisco(@RequestBody DiskDto discos) {
    try {
        if (discosService.addDisco(discos)) {
            return new AddDiscosResponse("01", "Successful registration", List.of(discos));
        } else {
            return new AddDiscosResponse("02", "Disk already exists", null);
        }
    } catch (Exception e) {
        e.printStackTrace();
        return new AddDiscosResponse("99", "Service not found", null);
    }
}

@DeleteMapping("/delete-discos")
public DeleteDiscosResponse deleteDisco(@RequestParam(value = "id", required = true) int id){
    try {
        if (discosService.deleteDiscoById(id)) {
            return new DeleteDiscosResponse("01", "Disk deleted successfully");
        } else {
            return new DeleteDiscosResponse("02", "Disk not found");
        }
    } catch (Exception e) {
        e.printStackTrace();
        return new DeleteDiscosResponse("99", "Service not found");
    }
}

}
