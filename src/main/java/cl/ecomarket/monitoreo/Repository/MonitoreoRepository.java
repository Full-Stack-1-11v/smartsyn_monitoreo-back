package cl.ecomarket.monitoreo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.ecomarket.monitoreo.Model.Monitoreo;

@Repository
public interface MonitoreoRepository extends JpaRepository<Monitoreo, Integer>{
    
    //Encontrar por id
    List<Monitoreo> findByid(Integer id);

    //Encontrar por descripcion
    Monitoreo findByDescripcion(String descripcion);

    //Encontrar por estado
    @Query("SELECT m FROM Monitoreo m WHERE m.estado = ?1")
    List<Monitoreo> findByEstado(Boolean estado);
}