package cl.ecomarket.monitoreo.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cl.ecomarket.monitoreo.Client.ReporteClient;
import cl.ecomarket.monitoreo.DTO.ReporteDTO;
import cl.ecomarket.monitoreo.Model.Monitoreo;
import cl.ecomarket.monitoreo.Repository.MonitoreoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MonitoreoService {

    private final ReporteClient reporteClient;
    private final MonitoreoRepository monitoreoRepository;

    private static final Logger logger = LoggerFactory.getLogger(MonitoreoService.class);

    public MonitoreoService(ReporteClient reporteClient, MonitoreoRepository monitoreoRepository) {
        this.reporteClient = reporteClient;
        this.monitoreoRepository = monitoreoRepository;
    }

    // Método para obtener el reporte desde el cliente Feign
    public List<ReporteDTO> obtenerReporte(){
        // INICIO
        logger.info("Llamando al cliente Feign para obtener el reporte");
        // CASO FELIZ
        logger.debug("Detalles del reporte obtenido: {}", reporteClient.getReporte());
        // CASO NO FELIZ
        logger.error("Error al obtener el reporte desde el cliente Feign");
        return reporteClient.getReporte();
    }

    public List<Monitoreo> findAll() {
        // INICIO
        logger.info("Obteniendo todos los monitoreos de la base de datos");
        // CASO FELIZ
        logger.debug("Detalles de los monitoreos obtenidos: {}", monitoreoRepository.findAll());
        // CASO NO FELIZ
        logger.error("Error al obtener los monitoreos de la base de datos");
        return monitoreoRepository.findAll();
    }

    public Monitoreo findById(Integer id) {
        // INICIO
        logger.info("Buscando monitoreo con id: {}", id);
        // CASO FELIZ
        logger.debug("Detalles de la búsqueda: {}", monitoreoRepository.findById(id));
        // CASO NO FELIZ
        logger.error("Monitoreo no encontrado a la base de datos con ID: ", id);
        return monitoreoRepository.findById(id).orElse(null);
    }

    public Monitoreo save(Monitoreo monitoreo) {
        // INICIO
        logger.info("Guardando monitoreo: {}", monitoreo);
        // CASO FELIZ
        logger.debug("Detalles del monitoreo guardado: {}", monitoreo);
        // CASO NO FELIZ
        logger.error("Error al guardar el monitoreo: {}", monitoreo);
        return monitoreoRepository.save(monitoreo);
    }

    public void deleteById(Integer id) {
        // INICIO
        logger.info("Eliminando monitoreo con id: {}", id);
        // CASO FELIZ
        logger.debug("Monitoreo eliminado con id: {}", id);
        // CASO NO FELIZ
        logger.error("Monitoreo no encontrado con id: {}", id);
        monitoreoRepository.deleteById(id);
    }

    // Cambiar estado por id
    public Monitoreo cambiarEstado(Integer id, boolean nuevoEstado) {
        // INICIO
        logger.info("Cambiando estado del monitoreo con id: {}", id);
        Monitoreo monitoreo = findById(id);
        if (monitoreo != null) {
            monitoreo.setEstado(nuevoEstado);
            // CASO FELIZ
            logger.debug("Estado del monitoreo con id {} cambiado a: {}", id, nuevoEstado);
            return monitoreoRepository.save(monitoreo);
        }
        // CASO NO FELIZ
        logger.error("Monitoreo no encontrado con id: {}", id);
        return null;
    }

    // Mostrar el estado por id
    public String mostrarEstado(Integer id) {
        // INICIO
        logger.info("Mostrando estado del monitoreo con id: {}", id);
        Monitoreo monitoreo = findById(id);
        if (monitoreo != null) {
            // CASO FELIZ
            logger.debug("Estado del monitoreo con id {} es: {}", id, monitoreo.getEstado());
            return "El estado es: " + monitoreo.getEstado();
        } else {
            // CASO NO FELIZ
            logger.error("No se encontró el monitoreo con id: {}", id);
            return "No se encontró el monitoreo con id: " + id;
        }
    }
    
    /* 
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
    */

}