package cl.ecomarket.monitoreo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.ecomarket.monitoreo.Model.Monitoreo;
import cl.ecomarket.monitoreo.Service.MonitoreoService;

@RestController
@RequestMapping("/api/v1/ecomarket/monitoreo")
public class MonitoreoController {

    @Autowired
    private MonitoreoService monitoreoService;

    @GetMapping
    public ResponseEntity<List<Monitoreo>> listar() {
        List<Monitoreo> monitoreos = monitoreoService.findAll();
        if (monitoreoService.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(monitoreos);
        }
    }

    @PostMapping
    public ResponseEntity<Monitoreo> guardar(@RequestBody Monitoreo monitoreo) {
        Monitoreo nuevoMonitoreo = monitoreoService.save(monitoreo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMonitoreo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Monitoreo> buscarPorId(@PathVariable Integer id) {
        try {
            Monitoreo monitoreo = monitoreoService.findById(id);
            return ResponseEntity.ok(monitoreo);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Monitoreo> actualizar(@PathVariable Integer id, @RequestBody Monitoreo monitoreo) {
        try {
            Monitoreo mon = monitoreoService.findById(id);
            mon.setId(id);
            mon.setDescripcion(monitoreo.getDescripcion());
            mon.setEstado(monitoreo.getEstado());

            monitoreoService.save(mon);
            return ResponseEntity.ok(mon);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            monitoreoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/cambiarEstado/{id}")
    public ResponseEntity<Monitoreo> cambiarEstado(@PathVariable Integer id, @RequestParam boolean nuevoEstado) {
        try {
            Monitoreo monitoreo = monitoreoService.cambiarEstado(id, nuevoEstado);
            return ResponseEntity.ok(monitoreo);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/mostrarEstado/{id}")
    public ResponseEntity<String> mostrarEstado(@PathVariable Integer id) {
        try {
            String estado = monitoreoService.mostrarEstado(id);
            return ResponseEntity.ok(estado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/solicitud/{id}")
    public ResponseEntity<String> solicitarDoc(@PathVariable Integer id) {
        try {
            String documentacion = monitoreoService.solicitarDocumentacion(id);
            return ResponseEntity.ok(documentacion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/enviarMonitoreo")
    public ResponseEntity<Monitoreo> enviarAReporte(@RequestBody Monitoreo monitoreo) {
        monitoreoService.enviarMonitoreoAReporte(monitoreo);
        return ResponseEntity.ok(monitoreo);
    }

    @GetMapping("/enviarMonitoreo")
    public ResponseEntity<Void> enviarMonitoreoAUserPorGet(
            @RequestParam("id") Integer id,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("estado") Boolean estado) {
        monitoreoService.enviarMonitoreoAUserPorGet(id, descripcion, estado);
        return ResponseEntity.ok().build();
    }

}
