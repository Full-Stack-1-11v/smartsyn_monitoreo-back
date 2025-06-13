package cl.ecomarket.monitoreo.Client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import cl.ecomarket.monitoreo.DTO.ReporteDTO;

@FeignClient(name = "reporte", url = "https://smartsync-reporte-back.onrender.com")
public interface ReporteClient {
    //POST para enviar datos como JSON
    // Se utiliza el DTO para enviar los datos
    @GetMapping("/api/v1/ecomarket/reporte")
    List<ReporteDTO> getReporte();
}
