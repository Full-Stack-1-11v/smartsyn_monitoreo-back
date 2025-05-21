package cl.EcoMarket.Monitoreo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.EcoMarket.Monitoreo.Model.Monitoreo;
import cl.EcoMarket.Monitoreo.Repository.MonitoreoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MonitoreoService {
    @Autowired
    private MonitoreoRepository monitoreoRepository;

    public List<Monitoreo> findAll() {
        return monitoreoRepository.findAll();
    }

    public Monitoreo findById(Integer id) {
        return monitoreoRepository.findById(id).orElse(null);
    }

    public Monitoreo save(Monitoreo monitoreo) {
        return monitoreoRepository.save(monitoreo);
    }

    public void deleteById(Integer id) {
        monitoreoRepository.deleteById(id);
    }

    // Cambiar estado por id
    public Monitoreo cambiarEstado(Integer id, boolean nuevoEstado) {
        Monitoreo monitoreo = findById(id);
        if (monitoreo != null) {
            monitoreo.setEstado(nuevoEstado);
            return monitoreoRepository.save(monitoreo);
        }
        return null;
    }

    // Mostrar el estado por id
    public String mostrarEstado(Integer id) {
        Monitoreo monitoreo = findById(id);
        if (monitoreo != null) {
            return "El estado es: " + monitoreo.getEstado();
        } else {
            return "No se encontró el monitoreo con id: " + id;
        }
    }

    // Solicitar una documentacion del monitoreo por id
    public String solicitarDocumentacion(Integer id) {
        Monitoreo monitoreo = findById(id);
        if (monitoreo != null) {
            return "ID: " + monitoreo.getId() +
                    "\nEstado Actual: " + monitoreo.getEstado() +
                    "\nDescripcion: " + monitoreo.getDescripcion() +
                    "\nSolicitud de Monitoreo enviada";
        } else {
            return "No se encontró el monitoreo con id: " + id;
        }
    }

}
