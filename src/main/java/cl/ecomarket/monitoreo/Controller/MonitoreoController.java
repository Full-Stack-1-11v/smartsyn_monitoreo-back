package cl.ecomarket.monitoreo.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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

import cl.ecomarket.monitoreo.Assemblers.MonitoreoModelAssembler;
import cl.ecomarket.monitoreo.DTO.ReporteDTO;
import cl.ecomarket.monitoreo.Model.Monitoreo;
import cl.ecomarket.monitoreo.Service.MonitoreoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/v1/ecomarket/monitoreo")
@Tag(name = "Monitoreo", description = "Controlador para gestionar el monitoreo de Ecomarket")
public class MonitoreoController {

    @Autowired
    private MonitoreoService monitoreoService;

    @Autowired
    private MonitoreoModelAssembler monitoreoModelAssembler;

    private static final Logger logger = LoggerFactory.getLogger(MonitoreoController.class);

    @GetMapping("/listar")
    @Operation(summary = "Listar Monitoreos", description = "Obtiene una lista de todos los monitoreos registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de monitoreos obtenida exitosamente.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Monitoreo.class), examples = @ExampleObject(value = "[{\"id\":1,\"descripcion\":\"Temperatura\",\"estado\":true},{\"id\":2,\"descripcion\":\"Humedad\",\"estado\":false}]"))),
            @ApiResponse(responseCode = "404", description = "No se encontraron monitoreos.")
    })
    public ResponseEntity<CollectionModel<EntityModel<Monitoreo>>> listar() {
        logger.info("Listando todos los monitoreos");
        List<Monitoreo> monitoreos = monitoreoService.findAll();
        List<EntityModel<Monitoreo>> modelos = monitoreos.stream()
                .map(monitoreoModelAssembler::toModel)
                .collect(Collectors.toList());
                logger.debug("Monitoreos encontrados: {}", modelos.size());
        return ResponseEntity.ok(CollectionModel.of(modelos,
                        linkTo(methodOn(MonitoreoController.class).listar()).withSelfRel()));      
    }

    @PostMapping("/guardar")
    @Operation(summary = "Guardar Monitoreo", description = "Crea un nuevo monitoreo en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Monitoreo creado exitosamente.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Monitoreo.class), examples = @ExampleObject(value = "{\"id\":3,\"descripcion\":\"Presión\",\"estado\":true}"))),
            @ApiResponse(responseCode = "400", description = "Error al crear el monitoreo.")
    })
    public ResponseEntity<EntityModel<Monitoreo>> guardar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del monitoreo a crear", required = true, content = @Content(schema = @Schema(implementation = Monitoreo.class), examples = @ExampleObject(value = "{\"descripcion\":\"Presión\",\"estado\":true}"))) @RequestBody Monitoreo monitoreo) {
        logger.info("Guardando nuevo monitoreo: {}", monitoreo);
        Monitoreo nuevoMonitoreo = monitoreoService.save(monitoreo);
        logger.debug("Nuevo monitoreo creado: {}", nuevoMonitoreo);
        return ResponseEntity.status(HttpStatus.CREATED).body(monitoreoModelAssembler.toModel(nuevoMonitoreo));
    }

    @GetMapping("/{id}/buscar")
    @Operation(summary = "Buscar Monitoreo por ID", description = "Obtiene un monitoreo específico por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Monitoreo encontrado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Monitoreo.class), examples = @ExampleObject(value = "{\"id\":1,\"descripcion\":\"Temperatura\",\"estado\":true}"))),
            @ApiResponse(responseCode = "404", description = "Monitoreo no encontrado.")
    })
    public ResponseEntity<EntityModel<Monitoreo>> buscarPorId(
        @Parameter(description = "ID del monitoreo", required = true, example = "1") @PathVariable Integer id) {
        logger.info("Buscando monitoreo con ID: {}", id);
        try {
            Monitoreo monitoreo = monitoreoService.findById(id);
            logger.debug("Monitoreo encontrado: {}", monitoreo);
            return ResponseEntity.ok(monitoreoModelAssembler.toModel(monitoreo));
        } catch (Exception e) {
            logger.error("Error al buscar monitoreo con ID {}: {}",id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/actualizar")
    @Operation(summary = "Actualizar Monitoreo", description = "Actualiza un monitoreo existente por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Monitoreo actualizado exitosamente.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Monitoreo.class), examples = @ExampleObject(value = "{\"id\":1,\"descripcion\":\"Temperatura actualizada\",\"estado\":false}"))),
            @ApiResponse(responseCode = "404", description = "Monitoreo no encontrado.")
    })
    public ResponseEntity<Monitoreo> actualizar(
            @Parameter(description = "ID del monitoreo", required = true, example = "1") @PathVariable Integer id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del monitoreo a actualizar", required = true, content = @Content(schema = @Schema(implementation = Monitoreo.class), examples = @ExampleObject(value = "{\"descripcion\":\"Temperatura actualizada\",\"estado\":false}"))) @RequestBody Monitoreo monitoreo) {
        logger.info("Actualizando monitoreo con ID: {}", id);
        try {
            Monitoreo mon = monitoreoService.findById(id);
            mon.setId(id);
            mon.setDescripcion(monitoreo.getDescripcion());
            mon.setEstado(monitoreo.getEstado());

            monitoreoService.save(mon);
            logger.debug("Monitoreo actualizado con ID: {}",id, mon);
            return ResponseEntity.ok(mon);
        } catch (Exception e) {
            logger.error("Monitoreo no encontrado con ID {}: {}",id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/eliminar")
    @Operation(summary = "Eliminar Monitoreo", description = "Elimina un monitoreo existente por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Monitoreo eliminado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Monitoreo no encontrado.")
    })
    public ResponseEntity<?> eliminar(
            @Parameter(description = "ID del monitoreo", required = true, example = "1") @PathVariable Integer id) {
        logger.info("Eliminando monitoreo con ID: {}", id);
        try {
            monitoreoService.deleteById(id);
            logger.debug("Monitoreo eliminado con ID: {}", id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Monitoreo no encontrado con ID {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/cambiarEstado")
    @Operation(summary = "Cambiar Estado del Monitoreo", description = "Cambia el estado (activo/inactivo) de un monitoreo por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado del monitoreo cambiado exitosamente.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Monitoreo.class), examples = @ExampleObject(value = "{\"id\":1,\"descripcion\":\"Temperatura\",\"estado\":true}"))),
            @ApiResponse(responseCode = "404", description = "Monitoreo no encontrado.")
    })
    public ResponseEntity<Monitoreo> cambiarEstado(
            @Parameter(description = "ID del monitoreo", required = true, example = "1") @PathVariable Integer id,
            @Parameter(description = "Nuevo estado (true=activo, false=inactivo)", required = true, example = "true") @RequestParam boolean nuevoEstado) {
        logger.info("Cambiando estado del monitoreo con ID: {} a {}", id, nuevoEstado);
        try {
            Monitoreo monitoreo = monitoreoService.cambiarEstado(id, nuevoEstado);
            logger.debug("Estado del monitoreo con ID {} cambiado a {}", id, nuevoEstado);
            return ResponseEntity.ok(monitoreo);
        } catch (Exception e) {
            logger.error("Monitoreo no encontrado con ID {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/mostrarEstado")
    @Operation(summary = "Mostrar Estado del Monitoreo", description = "Obtiene el estado de un monitoreo por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado del monitoreo obtenido exitosamente.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class), examples = @ExampleObject(value = "\"Activo\""))),
            @ApiResponse(responseCode = "404", description = "Monitoreo no encontrado.")
    })
    public ResponseEntity<String> mostrarEstado(
            @Parameter(description = "ID del monitoreo", required = true, example = "1") @PathVariable Integer id) {
        logger.info("Mostrando estado del monitoreo con ID: {}", id);
        try {
            String estado = monitoreoService.mostrarEstado(id);
            logger.debug("Estado del monitoreo con ID {}: {}", id, estado);
            return ResponseEntity.ok(estado);
        } catch (Exception e) {
            logger.error("Monitoreo no encontrado con ID {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/obtenerReporte")
    @Operation(summary = "Obtener Reporte de Monitoreos", description = "Obtiene un reporte de los monitoreos registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte obtenido exitosamente.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReporteDTO.class), examples = @ExampleObject(value = "[{\"id\":1,\"descripcion\":\"Temperatura\",\"estado\":true,\"total\":5}]"))),
            @ApiResponse(responseCode = "204", description = "No se encontraron reportes.")
    })
    public ResponseEntity<List<ReporteDTO>> listarReporte() {
        logger.info("Obteniendo reporte del monitoreo con sus detalles");
        List<ReporteDTO> reportes = monitoreoService.obtenerReporte();
        if (reportes.isEmpty()) {
            logger.debug("No se encontraron reportes de monitoreos");
            return ResponseEntity.noContent().build();
        } else {
            logger.debug("Reporte de monitores obtenidos con éxito: {}", reportes.size());
            return ResponseEntity.ok(reportes);
        }
    }

}
