package cl.ecomarket.monitoreo.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cl.ecomarket.monitoreo.DTO.MonitoreoDTO;

@FeignClient(name = "user", url = "${url.usuario}")
public interface ReporteClient {
    @PostMapping("api/v1/user/enviarMonitoreo")
    void enviarMonitoreo(@RequestBody MonitoreoDTO monitoreoDTO);
}
