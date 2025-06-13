package cl.ecomarket.monitoreo.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ReporteDTOTest {

    @Test
    public void testReporteDTOgetterAndSetter() {
        ReporteDTO reporte = new ReporteDTO();

        reporte.setId(1);
        reporte.setTipo("Tipo de Reporte");
        reporte.setFecha("2023-10-01");

        assertEquals(1, reporte.getId());
        assertEquals("Tipo de Reporte", reporte.getTipo());
        assertEquals("2023-10-01", reporte.getFecha());
    }

    @Test
    public void testEqualsAndHashCode() {
        ReporteDTO reporte1 = new ReporteDTO(1, "Tipo de Reporte", "2023-10-01");
        ReporteDTO reporte2 = new ReporteDTO(1, "Tipo de Reporte", "2023-10-01");
        ReporteDTO reporte3 = new ReporteDTO(2, "Otro Tipo", "2023-10-02");
        ReporteDTO reporte4 = new ReporteDTO(null, null, null);
        ReporteDTO reporte5 = new ReporteDTO(null, null, null);
        ReporteDTO reporte6 = new ReporteDTO();
        ReporteDTO reporte7 = new ReporteDTO();

        assertEquals(reporte1, reporte1);

        assertEquals(reporte1, reporte2);
        assertNotEquals(reporte1, reporte3);

        assertEquals(reporte1.hashCode(), reporte2.hashCode());
        assertNotEquals(reporte1.hashCode(), reporte3.hashCode());

        assertNotEquals(reporte1, null);

        assertNotEquals(reporte1, "String");
        assertNotEquals(reporte1.hashCode(), Integer.valueOf(20).hashCode());

        assertEquals(reporte4, reporte5);
        assertEquals(reporte4.hashCode(), reporte5.hashCode());
        assertNotEquals(reporte4.hashCode(), reporte3.hashCode());

        assertEquals(reporte6, reporte7);
        assertNotEquals(reporte6, reporte1);

    }

    @Test
    public void testEqualsWithNullFields() {
        // Ambos objetos con el mismo campo nulo
        ReporteDTO reporte1 = new ReporteDTO(1, "tipo", null);
        ReporteDTO reporte2 = new ReporteDTO(1, "tipo", null);
        assertTrue(reporte1.equals(reporte2));

        // Un objeto con campo nulo y otro con valor
        ReporteDTO reporte3 = new ReporteDTO(1, "tipo", null);
        ReporteDTO reporte4 = new ReporteDTO(1, "tipo", "2024-01-01");
        assertFalse(reporte3.equals(reporte4));

        // Ambos objetos con todos los campos nulos
        ReporteDTO reporte5 = new ReporteDTO(null, null, null);
        ReporteDTO reporte6 = new ReporteDTO(null, null, null);
        assertTrue(reporte5.equals(reporte6));
    }

    @Test
    public void testEqualsWithNullAndDifferentValues() {
        // Diferente id
        ReporteDTO r1 = new ReporteDTO(1, "tipo", null);
        ReporteDTO r2 = new ReporteDTO(2, "tipo", null);
        assertFalse(r1.equals(r2));

        // Diferente tipo
        ReporteDTO r3 = new ReporteDTO(1, "tipoA", null);
        ReporteDTO r4 = new ReporteDTO(1, "tipoB", null);
        assertFalse(r3.equals(r4));

        // Diferente fecha (uno null, otro con valor)
        ReporteDTO r5 = new ReporteDTO(1, "tipo", null);
        ReporteDTO r6 = new ReporteDTO(1, "tipo", "2024-01-01");
        assertFalse(r5.equals(r6));

        // Todos los campos diferentes
        ReporteDTO r7 = new ReporteDTO(1, "A", null);
        ReporteDTO r8 = new ReporteDTO(2, "B", "2024-01-01");
        assertFalse(r7.equals(r8));
    }

    @Test
    public void testEqualsWithNull() {
        ReporteDTO reporte = new ReporteDTO(1, "tipo", "2024-01-01");
        assertFalse(reporte.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        ReporteDTO reporte = new ReporteDTO(1, "tipo", "2024-01-01");
        String otroObjeto = "No es un ReporteDTO";
        assertFalse(reporte.equals(otroObjeto));
    }

    @Test
    public void testToString() {
        ReporteDTO reporte = new ReporteDTO(1, "Tipo de Reporte", "2023-10-01");
        String expectedString = "ReporteDTO(id=1, tipo=Tipo de Reporte, fecha=2023-10-01)";
        assertEquals(expectedString, reporte.toString());
    }
}
