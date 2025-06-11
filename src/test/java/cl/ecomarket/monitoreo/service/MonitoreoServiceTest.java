package cl.ecomarket.monitoreo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import cl.ecomarket.monitoreo.Client.ReporteClient;
import cl.ecomarket.monitoreo.DTO.reporteDTO;
import cl.ecomarket.monitoreo.Model.Monitoreo;
import cl.ecomarket.monitoreo.Repository.MonitoreoRepository;
import cl.ecomarket.monitoreo.Service.MonitoreoService;

@SpringBootTest
@ActiveProfiles("test")
public class MonitoreoServiceTest {
    
    @Autowired
    private MonitoreoService monitoreoService;

    @MockBean
    private MonitoreoRepository monitoreoRepository;

    @MockBean
    private ReporteClient reporteClient;

    // Aquí puedes agregar tus pruebas unitarias o de integración para el servicio MonitoreoService
    // Por ejemplo, podrías probar métodos específicos del servicio, asegurándote de que se comporten como se espera.
    @Test
    public void testGetMonitoreo(){
        //Given
        List<Monitoreo> monitoreos = new ArrayList<>();
        monitoreos.add(new Monitoreo(1, "Monitoreo 1", true));

        //When
        when(monitoreoRepository.findAll()).thenReturn(monitoreos);

        //Then
        List<Monitoreo> result = monitoreoService.findAll();

        assertEquals(monitoreos, result);
        assertEquals(1, result.size()); // Asegúrate de que la lista no esté vacía
    }

    @Test
    public void testGetFindById(){
        //Given
        Optional<Monitoreo> monitoreo = java.util.Optional.of(new Monitoreo(1, "Monitoreo 1", true));

        //When
        when(monitoreoRepository.findById(1)).thenReturn(monitoreo);

        //Then
        Monitoreo result = monitoreoService.findById(1);
        
        assertEquals(monitoreo.get(), result); // Asegúrate de que el monitoreo encontrado sea el mismo que el original
        assertEquals(monitoreo.get().getId(), result.getId()); // Asegúrate de que el ID sea correcto
        assertEquals(monitoreo.get().getDescripcion(), result.getDescripcion()); // Asegúrate de que la descripción no esté vacía
        assertEquals(monitoreo.get().getEstado(), result.getEstado()); // Asegúrate de que el estado sea correcto
    }

    @Test
    public void testSaveMonitoreo(){

        //Given
        Monitoreo monitoreo = new Monitoreo(1, "Monitoreo 1", true);

        //When
        when(monitoreoRepository.save(monitoreo)).thenReturn(monitoreo);

        //Then
        Monitoreo result = monitoreoService.save(monitoreo);

        assertEquals(monitoreo, result); // Asegúrate de que el monitoreo guardado sea el mismo que el original
        assertEquals(monitoreo.getId(), result.getId()); // Asegúrate de que el ID sea correcto
        assertEquals(monitoreo.getDescripcion(), result.getDescripcion()); // Asegúrate de que la descripción no esté vacía
        assertEquals(monitoreo.getEstado(), result.getEstado()); // Asegúrate de que el estado sea correcto
    }

    @Test
    public void cambiarEstadoMonitoreo(){
        //Given 
        Optional<Monitoreo> monitoreo = java.util.Optional.of(new Monitoreo(1, "Monitoreo 1", true));

        //when
        when(monitoreoRepository.findById(1)).thenReturn(monitoreo);
        when(monitoreoRepository.save(monitoreo.get())).thenReturn(monitoreo.get());

        //Then
        Monitoreo result = monitoreoService.cambiarEstado(1, false);

        assertEquals(monitoreo.get().getEstado(), result.getEstado()); //El estado debe ser el mismo que el original
        assertEquals(false, result.getEstado()); //El estado debe ser el nuevo estado que se le asignó
        assertEquals(monitoreo.get().getId(), result.getId()); // Asegúrate de que el ID sea correcto
        assertEquals(monitoreo.get().getDescripcion(), result.getDescripcion()); // Asegúrate de que la descripción no esté vacía
        assertNotNull(result); //Asegurando que el resultado no sea nulo
    }

    @Test
    public void mostrarEstadoMonitoreo(){
        //Given
        Optional<Monitoreo> monitoreo = java.util.Optional.of(new Monitoreo(1, "Monitoreo 1", true));

        //When
        when(monitoreoRepository.findById(1)).thenReturn(monitoreo);

        //Then
        String result = monitoreoService.mostrarEstado(1);

        assertEquals("El estado es: " + monitoreo.get().getEstado(), result); //Asegurando que el resultado sea el mismo 
        assertEquals(monitoreo.get().getId(), 1); // Asegurando que el ID sea correcto
        assertEquals(monitoreo.get().getEstado(), true); //Asegurando que el estado sea correcto
        assertNotNull(result); //Asegurando que el resultado no sea nulo
        
    }

    @Test
    public void mostrarNullEstadoMonitoreo(){
        //Given
        Optional<Monitoreo> monitoreo = java.util.Optional.empty();

        //When
        when(monitoreoRepository.findById(1)).thenReturn(monitoreo);

        //Then
        String result = monitoreoService.mostrarEstado(null);

        assertEquals("No se encontró el monitoreo con id: null", result); // Asegurando que el resultado sea el mensaje de error correcto
        assertNotNull(result); // Asegurando que el resultado no sea nulo
    }

    @Test
    public void cambiarEstadoNullMonitoreo(){
        //Given
        Optional<Monitoreo> monitoreo = java.util.Optional.empty();

        //When
        when(monitoreoRepository.findById(1)).thenReturn(monitoreo);

        //Then
        Monitoreo result = monitoreoService.cambiarEstado(1, false);

        assertEquals(null, result); // Asegurando que el resultado sea nulo ya que no se encontró el monitoreo
    }

    @Test
    public void deleteByIdMonitoreo(){
        //Given
        Integer id = 1;

        //When
        monitoreoService.deleteById(id);
    }

    @Test
    public void obtenerReporteDTO(){
        //Given
        List<reporteDTO> reportes = new ArrayList<>();
        reportes.add(new reporteDTO(1, "Tipo 1", "2023-10-01"));

        //When
        when(monitoreoService.obtenerReporte()).thenReturn(reportes);

        //Then
        List<reporteDTO> result = monitoreoService.obtenerReporte();

        assertNotNull(result); // Asegurando que el resultado no sea nulo

    }



}
