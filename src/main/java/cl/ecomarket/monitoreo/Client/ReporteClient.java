package cl.ecomarket.monitoreo.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cl.ecomarket.monitoreo.DTO.MonitoreoDTO;

@FeignClient(name = "user", url = "${https://smartsync-usuario-back-pruebas.onrender.com}")
public interface ReporteClient {
    @GetMapping("/api/v1/ecomarket/user/monitoreo")
    void enviarMonitoreo(@RequestBody MonitoreoDTO monitoreoDTO);
}
