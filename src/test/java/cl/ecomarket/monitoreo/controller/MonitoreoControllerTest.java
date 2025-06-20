package cl.ecomarket.monitoreo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import cl.ecomarket.monitoreo.Controller.MonitoreoController;
import cl.ecomarket.monitoreo.Service.MonitoreoService;
import cl.ecomarket.monitoreo.Model.Monitoreo;
import cl.ecomarket.monitoreo.DTO.ReporteDTO;

@WebMvcTest(MonitoreoController.class)
public class MonitoreoControllerTest {

    @MockBean
    private MonitoreoService monitoreoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/v1/ecomarket/monitoreo/listar retorna 200 y una lista de Monitoreos")
    void listarDebeRetornarLista() throws Exception {
        Monitoreo monitoreo1 = new Monitoreo(); // Asegúrate de tener un constructor vacío o mockea los datos
        Monitoreo monitoreo2 = new Monitoreo();
        when(monitoreoService.findAll()).thenReturn(Arrays.asList(monitoreo1, monitoreo2));

        mockMvc.perform(get("/api/v1/ecomarket/monitoreo/listar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @DisplayName("POST /api/v1/ecomarket/monitoreo/crear retorna 201 al crear un Monitoreo")
    void crearDebeRetornarCreado() throws Exception {
        Monitoreo monitoreo = new Monitoreo(1, "Monitoreo 1", true);

        when(monitoreoService.save(monitoreo)).thenReturn(monitoreo);
        mockMvc.perform(post("/api/v1/ecomarket/monitoreo/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"descripcion\":\"Monitoreo 1\",\"estado\":true}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descripcion").value("Monitoreo 1"))
                .andExpect(jsonPath("$.estado").value(true));
    }

    @Test
    @DisplayName("GET /api/v1/ecomarket/monitoreo/{id}/buscar retorna 200 al buscar un Monitoreo por ID")
    void buscarPorIdDebeRetornarMonitoreo() throws Exception {
        Monitoreo monitoreo = new Monitoreo(1, "Monitoreo 1", true);
        when(monitoreoService.findById(1)).thenReturn(monitoreo);

        mockMvc.perform(get("/api/v1/ecomarket/monitoreo/1/buscar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descripcion").value("Monitoreo 1"))
                .andExpect(jsonPath("$.estado").value(true));
    }

    @Test
    @DisplayName("GET /api/v1/ecomarket/monitoreo/{id}/buscar retorna 404 si no se encuentra el Monitoreo")
    void buscarPorIdDebeRetornarNotFound() throws Exception {
        when(monitoreoService.findById(1)).thenThrow(new RuntimeException("Monitoreo no encontrado"));

        mockMvc.perform(get("/api/v1/ecomarket/monitoreo/1/buscar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("PUT /api/v1/ecomarket/monitoreo/{id}/actualizar retorna 404 si no existe el Monitoreo")
    void actualizarDebeRetornarNotFound() throws Exception {
        when(monitoreoService.findById(1)).thenThrow(new RuntimeException("No encontrado"));

        mockMvc.perform(put("/api/v1/ecomarket/monitoreo/1/actualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"descripcion\":\"Actualizado\",\"estado\":false}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("PUT /api/v1/ecomarket/monitoreo/{id}/actualizar retorna 200 al actualizar un Monitoreo")
    void actualizarDebeRetornarOk() throws Exception {
        Monitoreo original = new Monitoreo(1, "Original", true);
        Monitoreo actualizado = new Monitoreo(1, "Actualizado", false);

        when(monitoreoService.findById(1)).thenReturn(original);
        when(monitoreoService.save(any(Monitoreo.class))).thenReturn(actualizado);

        mockMvc.perform(put("/api/v1/ecomarket/monitoreo/1/actualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"descripcion\":\"Actualizado\",\"estado\":false}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descripcion").value("Actualizado"))
                .andExpect(jsonPath("$.estado").value(false));
    }

    @Test
    @DisplayName("DELETE /api/v1/ecomarket/monitoreo/{id}/eliminar retorna 204 al eliminar un Monitoreo")
    void eliminarDebeRetornarNoContent() throws Exception {
        mockMvc.perform(delete("/api/v1/ecomarket/monitoreo/1/eliminar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /api/v1/ecomarket/monitoreo/{id}/eliminar retorna 404 si no existe el Monitoreo")
    void eliminarDebeRetornarNotFound() throws Exception {
        // Simula que el servicio lanza una excepción al eliminar
        doThrow(new RuntimeException("No encontrado")).when(monitoreoService).deleteById(1);

        mockMvc.perform(delete("/api/v1/ecomarket/monitoreo/1/eliminar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("PUT /api/v1/ecomarket/monitoreo/{id}/cambiarEstado retorna 200 al cambiar el estado de un Monitoreo")
    void cambiarEstadoDebeRetornarOk() throws Exception {
        when(monitoreoService.cambiarEstado(1, false)).thenReturn(new Monitoreo(1, "Monitoreo 1", false));

        mockMvc.perform(put("/api/v1/ecomarket/monitoreo/1/cambiarEstado?nuevoEstado=false")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descripcion").value("Monitoreo 1"))
                .andExpect(jsonPath("$.estado").value(false));
    }

    @Test
    @DisplayName("PUT /api/v1/ecomarket/monitoreo/{id}/cambiarEstado retorna 404 si no existe el Monitoreo")
    void cambiarEstadoDebeRetornarNotFound() throws Exception {
        when(monitoreoService.cambiarEstado(1, false)).thenThrow(new RuntimeException("Monitoreo no encontrado"));

        mockMvc.perform(put("/api/v1/ecomarket/monitoreo/1/cambiarEstado?nuevoEstado=false")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /api/v1/ecomarket/monitoreo/{id}/mostrarEstado retorna 200 al mostrar el estado de un Monitoreo")
    void mostrarEstadoDebeRetornarOk() throws Exception {
        when(monitoreoService.mostrarEstado(1)).thenReturn("Activo");

        mockMvc.perform(get("/api/v1/ecomarket/monitoreo/1/mostrarEstado")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("Activo"))
                .andExpect(jsonPath("$._links.self.href").exists())
                .andExpect(jsonPath("$._links.monitoreos.href").exists());
    }

    @Test
    @DisplayName("GET /api/v1/ecomarket/monitoreo/{id}/mostrarEstado retorna 404 si no existe el Monitoreo")
    void mostrarEstadoDebeRetornarNotFound() throws Exception {
        when(monitoreoService.mostrarEstado(1)).thenThrow(new RuntimeException("Monitoreo no encontrado"));

        mockMvc.perform(get("/api/v1/ecomarket/monitoreo/1/mostrarEstado")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET api/v1/ecomarket/monitoreo/obtenerReporte retorna 200 y un reporte de Monitoreos")
    void listarReporteDebeRetornarOk() throws Exception {
        ReporteDTO reporte = new ReporteDTO(); // Asegúrate de tener un constructor vacío o usa un mock
        when(monitoreoService.obtenerReporte()).thenReturn(List.of(reporte));

        mockMvc.perform(get("/api/v1/ecomarket/monitoreo/obtenerReporte")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded").exists())
                .andExpect(jsonPath("$._links.self.href").exists());
    }

    @Test
    @DisplayName("GET /api/v1/ecomarket/monitoreo/obtenerReporte retorna 204 si no hay reportes")
    void listarReporteDebeRetornarNoContent() throws Exception {
        when(monitoreoService.obtenerReporte()).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/ecomarket/monitoreo/obtenerReporte")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
