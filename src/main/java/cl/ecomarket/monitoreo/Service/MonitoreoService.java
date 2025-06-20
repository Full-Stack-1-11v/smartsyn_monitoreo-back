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

    /*
     * La clase MonitoreoService es un servicio que maneja la lógica de negocio relacionada con los monitoreos.
     * Utiliza un cliente Feign para obtener reportes de un servicio externo y un repositorio JPA para realizar operaciones CRUD sobre la entidad Monitoreo.
     * Proporciona métodos para obtener reportes, buscar, guardar, eliminar y cambiar el estado de los monitoreos, así como para mostrar su estado actual.
     */
    private final ReporteClient reporteClient;
    private final MonitoreoRepository monitoreoRepository;

    /*
     * Logger para registrar información, advertencias y errores en la aplicación.
     */
    private static final Logger logger = LoggerFactory.getLogger(MonitoreoService.class);

    /*
     * Constructor de la clase MonitoreoService.
     */
    public MonitoreoService(ReporteClient reporteClient, MonitoreoRepository monitoreoRepository) {
        this.reporteClient = reporteClient;
        this.monitoreoRepository = monitoreoRepository;
    }

    /*
     * Método para obtener un reporte desde el cliente Feign.
     * Utiliza el cliente ReporteClient para realizar una llamada GET al servicio externo y obtener una lista de ReporteDTO.
     * Obtiene una lista de reportes registrados.
     * @return Lista de Objetos {@link ReporteDTO}
     */
    public List<ReporteDTO> obtenerReporte(){
        // INICIO
        logger.info("Llamando al cliente Feign para obtener el reporte");
        // CASO FELIZ
        logger.debug("Detalles del reporte obtenido: {}", reporteClient.getReporte());
        // CASO NO FELIZ
        logger.error("Error al obtener el reporte desde el cliente Feign");
        return reporteClient.getReporte();
    }

    /*
     * Método para obtener todos los monitoreos de la base de datos.
     * Utiliza el repositorio MonitoreoRepository para realizar una consulta y obtener una lista de Monitoreo.
     * @return Lista de Objetos {@link Monitoreo}
     */
    public List<Monitoreo> findAll() {
        // INICIO
        logger.info("Obteniendo todos los monitoreos de la base de datos");
        // CASO FELIZ
        logger.debug("Detalles de los monitoreos obtenidos: {}", monitoreoRepository.findAll());
        // CASO NO FELIZ
        logger.error("Error al obtener los monitoreos de la base de datos");
        return monitoreoRepository.findAll();
    }

    /*
     * Método para buscar un monitoreo por su identificador único.
     * Utiliza el repositorio MonitoreoRepository para realizar una consulta y obtener un Monitoreo por su id.
     * @param id Identificador único del monitoreo a buscar.
     * @return Objeto {@link Monitoreo} si se encuentra, o {@code null} si no se encuentra.
     */
    public Monitoreo findById(Integer id) {
        // INICIO
        logger.info("Buscando monitoreo con id: {}", id);
        // CASO FELIZ
        logger.debug("Detalles de la búsqueda: {}", monitoreoRepository.findById(id));
        // CASO NO FELIZ
        logger.error("Monitoreo no encontrado a la base de datos con ID: ", id);
        return monitoreoRepository.findById(id).orElse(null);
    }

    /*
     * Guarda un monitoreo en la base de datos.
     * Utiliza el repositorio MonitoreoRepository para realizar una operación de guardado.
     * @param monitoreo Objeto {@link Monitoreo} a guardar.
     * @return Objeto {@link Monitoreo} guardado.
     */
    public Monitoreo save(Monitoreo monitoreo) {
        // INICIO
        logger.info("Guardando monitoreo: {}", monitoreo);
        // CASO FELIZ
        logger.debug("Detalles del monitoreo guardado: {}", monitoreo);
        // CASO NO FELIZ
        logger.error("Error al guardar el monitoreo: {}", monitoreo);
        return monitoreoRepository.save(monitoreo);
    }

    /*
     * Método para eliminar un monitoreo por su identificador único.
     * Utiliza el repositorio MonitoreoRepository para realizar una eliminación por id.
     * @param id Identificador único del monitoreo a eliminar.
     */
    public void deleteById(Integer id) {
        // INICIO
        logger.info("Eliminando monitoreo con id: {}", id);
        // CASO FELIZ
        logger.debug("Monitoreo eliminado con id: {}", id);
        // CASO NO FELIZ
        logger.error("Monitoreo no encontrado con id: {}", id);
        monitoreoRepository.deleteById(id);
    }

    /*
     * Método para cambiar el estado de un monitoreo por su identificador único.
     * Utiliza el repositorio MonitoreoRepository para buscar el monitoreo por id. 
     * @param id Identificador único del monitoreo cuyo estado se desea cambiar.
     * @param nuevoEstado Nuevo estado a asignar al monitoreo.
     * @return Objeto {@link Monitoreo} actualizado si se encuentra, o {@code null} si no se encuentra.
     */
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

    /*
     * Método para mostrar el estado de un monitoreo por su identificador único.
     * Utiliza el repositorio MonitoreoRepository para realizar una consulta y obtener un Monitoreo por su id.
     * @param id Identificador único del monitoreo cuyo estado se desea mostrar.
     * @return Estado del monitoreo como {@link String} si se encuentra, o un mensaje de error si no se encuentra.
     */
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

}