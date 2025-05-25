package cl.ecomarket.monitoreo.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cl.ecomarket.monitoreo.Model.Monitoreo;

@FeignClient(name = "reporte", url = "${https://smartsync-producto-back.onrender.com}")
public interface ReporteClient {
    @GetMapping("/api/v1/ecomarket/reporte/monitoreo")
    void enviarMonitoreo(@RequestBody Monitoreo monitoreo);
}
