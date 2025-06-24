package pe.edu.cibertec.ProyectoFinalDiscos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.ProyectoFinalDiscos.dto.DiscoDto;
import pe.edu.cibertec.ProyectoFinalDiscos.dto.DiskDto;
import pe.edu.cibertec.ProyectoFinalDiscos.entity.Disco;
import pe.edu.cibertec.ProyectoFinalDiscos.repository.DiscoRepository;
import pe.edu.cibertec.ProyectoFinalDiscos.service.DiscosService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiscosServiceImpl implements DiscosService {

    @Autowired
    DiscoRepository discoRepository;

    @Override
    public List<DiscoDto> getAllDiscos() throws Exception {
        List<DiscoDto> discos = new ArrayList<DiscoDto>();
        Iterable<Disco> iterable = discoRepository.findAll();
        iterable.forEach(disco -> {
            DiscoDto discoDto= new DiscoDto(
                    disco.getId(),
                    disco.getArtista(),
                    disco.getDiscografica(),
                    disco.getAnio(),
                    disco.getPrecio(),
                    disco.getGenero(),
                    disco.getNombre());
            discos.add(discoDto);

        });
        return discos;
    }

    @Override
    public Optional<DiscoDto> getDiscoById(int id) throws Exception {
        Optional<Disco> optional = discoRepository.findById(id);
        return optional.map(disco -> new DiscoDto(
                disco.getId(),
                disco.getArtista(),
                disco.getDiscografica(),
                disco.getAnio(),
                disco.getPrecio(),
                disco.getGenero(),
                disco.getNombre()));
    }

    @Override
    public boolean updateDisco(DiscoDto discoDto) throws Exception {
        Optional<Disco> optional = discoRepository.findById(discoDto.id());
        return optional.map(disco -> {
            disco.setArtista(discoDto.artista());
            disco.setDiscografica(discoDto.discografica());
            disco.setAnio(discoDto.anio());
            disco.setPrecio(discoDto.precio());
            disco.setGenero(discoDto.genero());
            disco.setNombre(discoDto.nombre());
            discoRepository.save(disco);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean deleteDiscoById(int id) throws Exception {
        Optional<Disco> optional = discoRepository.findById(id);
        return optional.map(disco -> {
            discoRepository.delete(disco);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean addDisco(DiskDto discoDto) throws Exception {
        Disco disco = new Disco();
        disco.setArtista(discoDto.artista());
        disco.setDiscografica(discoDto.discografica());
        disco.setAnio(discoDto.anio());
        disco.setPrecio(discoDto.precio());
        disco.setGenero(discoDto.genero());
        disco.setNombre(discoDto.nombre());
        discoRepository.save(disco);
        return true;

    }

}
