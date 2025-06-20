package cl.ecomarket.monitoreo.Client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import cl.ecomarket.monitoreo.DTO.ReporteDTO;

/**
 * La interfaz ReporteClient es un cliente Feign que se utiliza para realizar llamadas HTTP a un servicio externo de reporte.
 * Permite obtener una lista de reportes a través de un método GET.
 */
@FeignClient(name = "reporte", url = "https://smartsync-reporte-back.onrender.com")
public interface ReporteClient {
    /**
     * POST para enviar datos como JSON
     * Se utiliza el DTO para enviar los datos
     */
    @GetMapping("/api/v1/ecomarket/reporte")
    List<ReporteDTO> getReporte();
}
