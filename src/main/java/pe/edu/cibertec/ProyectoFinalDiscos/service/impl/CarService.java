package pe.edu.cibertec.ProyectoFinalDiscos.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import pe.edu.cibertec.ProyectoFinalDiscos.dto.DiscoDto;

import java.util.HashMap;
import java.util.Map;

@Service
@SessionScope
public class CarService {

        private Map<DiscoDto, Integer> items = new HashMap<>(); // Mapa para almacenar discos y sus cantidades

        public void agregarDisco(DiscoDto disco) {
            items.put(disco, items.getOrDefault(disco, 0) + 1); // Incrementa la cantidad si ya existe
        }

        public void eliminarDisco(int id) {
            items.keySet().removeIf(disco -> disco.id().equals(id));
        }

        public void actualizarCantidad(int id, int cantidad) {
            items.keySet().stream()
                    .filter(disco -> disco.id().equals(id))
                    .findFirst()
                    .ifPresent(disco -> {
                        if (cantidad > 0) {
                            items.put(disco, cantidad);
                        } else {
                            items.remove(disco);
                        }
                    });
        }

        public void vaciarCarrito() {
            items.clear();
        }

        public Map<DiscoDto, Integer> getItems() {
            return items;
        }

        public int getCantidadTotal() {
            return items.values().stream().mapToInt(Integer::intValue).sum();
        }

        public double getTotalPagar() {
            return items.entrySet().stream()
                    .mapToDouble(entry -> entry.getKey().precio() * entry.getValue())
                    .sum();
        }
}
