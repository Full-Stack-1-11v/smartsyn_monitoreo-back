package cl.ecomarket.monitoreo.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import cl.ecomarket.monitoreo.DTO.MonitoreoDTO;

@FeignClient(name = "user", url = "${url.usuario}")
public interface ReporteClient {
    //POST para enviar datos como JSON
    // Se utiliza el DTO para enviar los datos
    @PostMapping("/api/v1/monitoreo/enviarMonitoreo")
    void enviarMonitoreo(@RequestBody MonitoreoDTO monitoreoDTO);

    // GET para enviar datos como parámetros
    @GetMapping("/api/v1/monitoreo/enviarMonitoreo")
    void enviarMonitoreoGet(
        @RequestParam("id") Integer id,
        @RequestParam("descripcion") String descripcion,
        @RequestParam("estado") Boolean estado
    );
}
