package cl.ecomarket.monitoreo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

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



}
