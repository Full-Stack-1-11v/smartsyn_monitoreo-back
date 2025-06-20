package cl.ecomarket.monitoreo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.ecomarket.monitoreo.Model.Monitoreo;

/*
 * La interfaz MonitoreoRepository es un repositorio JPA que permite realizar operaciones CRUD sobre la entidad Monitoreo.
 * Proporciona métodos para encontrar monitoreos por id, descripción y estado.
 */

@Repository
public interface MonitoreoRepository extends JpaRepository<Monitoreo, Integer>{
    
    /**
     * Este método permite encontrar un monitoreo por su identificador único.
     */
    List<Monitoreo> findByid(Integer id);

    /**
     * Este método permite encontrar un monitoreo por su descripción.
     */
    Monitoreo findByDescripcion(String descripcion);

    /**
     * Este método permite encontrar una lista de monitoreos por su estado (activo o inactivo).
     */
    @Query("SELECT m FROM Monitoreo m WHERE m.estado = ?1")
    List<Monitoreo> findByEstado(Boolean estado);
}