package cl.ecomarket.monitoreo.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import cl.ecomarket.monitoreo.Model.Monitoreo;

@SpringBootTest
@ActiveProfiles("test")
public class MonitoreoTest {

    @Test
    public void testMonitoreoGetterAndSetter() {
        Monitoreo monitoreo = new Monitoreo();
        monitoreo.setId(1);
        monitoreo.setDescripcion("This is a test description");
        monitoreo.setEstado(true);

        assert monitoreo.getId() == 1L;
        assert "This is a test description".equals(monitoreo.getDescripcion());
        assert monitoreo.getEstado() == true;
    }

    @Test
    public void testMonitoreoToString() {
        Monitoreo monitoreo = new Monitoreo();
        monitoreo.setId(1);
        monitoreo.setDescripcion("This is a test description");
        monitoreo.setEstado(true);

        String expectedString = "Monitoreo(id=1, descripcion=This is a test description, estado=true)";
        assert expectedString.equals(monitoreo.toString());
    }

    @Test
    public void testMonitoreoEqualsAndHashCode() {
        Monitoreo monitoreo1 = new Monitoreo();
        monitoreo1.setId(1);
        monitoreo1.setDescripcion("This is a test description");
        monitoreo1.setEstado(true);

        Monitoreo monitoreo2 = new Monitoreo();
        monitoreo2.setId(1);
        monitoreo2.setDescripcion("This is a test description");
        monitoreo2.setEstado(true);

        assert monitoreo1.equals(monitoreo2);
        assert monitoreo1.hashCode() == monitoreo2.hashCode();
    }

    @Test
    public void testMonitoreoNotEquals() {
        Monitoreo monitoreo1 = new Monitoreo();
        monitoreo1.setId(1);
        monitoreo1.setDescripcion("This is a test description");
        monitoreo1.setEstado(true);

        Monitoreo monitoreo2 = new Monitoreo();
        monitoreo2.setId(2);
        monitoreo2.setDescripcion("This is another description");
        monitoreo2.setEstado(false);

        assert !monitoreo1.equals(monitoreo2);
    }

    @Test
    public void testMonitoreoWithNullValues() {
        Monitoreo monitoreo = new Monitoreo();
        monitoreo.setId(null);
        monitoreo.setDescripcion(null);
        monitoreo.setEstado(null);

        assert monitoreo.getId() == null;
        assert monitoreo.getDescripcion() == null;
        assert monitoreo.getEstado() == null;
    }

    @Test
    public void testMonitoreoWithEmptyDescription() {
        Monitoreo monitoreo = new Monitoreo();
        monitoreo.setId(1);
        monitoreo.setDescripcion("");
        monitoreo.setEstado(true);

        assert monitoreo.getId() == 1L;
        assert "".equals(monitoreo.getDescripcion());
        assert monitoreo.getEstado() == true;
    }

    @Test
    public void testMonitoreoWithFalseState() {
        Monitoreo monitoreo = new Monitoreo();
        monitoreo.setId(1);
        monitoreo.setDescripcion("This is a test description");
        monitoreo.setEstado(false);

        assert monitoreo.getId() == 1L;
        assert "This is a test description".equals(monitoreo.getDescripcion());
        assert monitoreo.getEstado() == false;
    }

    @Test
    public void testEqualsWithNull() {
        Monitoreo monitoreo = new Monitoreo();
        monitoreo.setId(1);
        monitoreo.setDescripcion("This is a test description");
        monitoreo.setEstado(true);

        assert !monitoreo.equals(null);
    }

    @Test
    public void testEqualsWithDifferentClass() {
        Monitoreo monitoreo = new Monitoreo();
        monitoreo.setId(1);
        monitoreo.setDescripcion("This is a test description");
        monitoreo.setEstado(true);

        String differentClassObject = "Not a Monitoreo object";
        assert !monitoreo.equals(differentClassObject);
    }

    @Test
    public void testToStringWithNullValues() {
        Monitoreo monitoreo = new Monitoreo();
        monitoreo.setId(null);
        monitoreo.setDescripcion(null);
        monitoreo.setEstado(null);

        String expectedString = "Monitoreo(id=null, descripcion=null, estado=null)";
        assert expectedString.equals(monitoreo.toString());
    }

    @Test
    public void testEqualsDifferentDescriptions() {
        Monitoreo monitoreo1 = new Monitoreo();
        monitoreo1.setId(1);
        monitoreo1.setDescripcion("Description 1");
        monitoreo1.setEstado(true);

        Monitoreo monitoreo2 = new Monitoreo();
        monitoreo2.setId(1);
        monitoreo2.setDescripcion("Description 2");
        monitoreo2.setEstado(true);

        assert !monitoreo1.equals(monitoreo2);
    }

    @Test
    public void testEqualsDifferentStates() {
        Monitoreo monitoreo1 = new Monitoreo();
        monitoreo1.setId(1);
        monitoreo1.setDescripcion("Description");
        monitoreo1.setEstado(true);

        Monitoreo monitoreo2 = new Monitoreo();
        monitoreo2.setId(1);
        monitoreo2.setDescripcion("Description");
        monitoreo2.setEstado(false);

        assert !monitoreo1.equals(monitoreo2);
    }

    @Test
    public void testHashCodeWithDifferentDescriptions() {
        Monitoreo monitoreo1 = new Monitoreo();
        monitoreo1.setId(1);
        monitoreo1.setDescripcion("Description 1");
        monitoreo1.setEstado(true);

        Monitoreo monitoreo2 = new Monitoreo();
        monitoreo2.setId(1);
        monitoreo2.setDescripcion("Description 2");
        monitoreo2.setEstado(true);

        assert monitoreo1.hashCode() != monitoreo2.hashCode();
    }

    @Test
    public void testHashCodeWithDifferentStates() {
        Monitoreo monitoreo1 = new Monitoreo();
        monitoreo1.setId(1);
        monitoreo1.setDescripcion("Description");
        monitoreo1.setEstado(true);

        Monitoreo monitoreo2 = new Monitoreo();
        monitoreo2.setId(1);
        monitoreo2.setDescripcion("Description");
        monitoreo2.setEstado(false);

        assert monitoreo1.hashCode() != monitoreo2.hashCode();
    }

    @Test
    public void testMonitoreoWithLongId() {
        Monitoreo monitoreo = new Monitoreo();
        monitoreo.setId(123456789);
        monitoreo.setDescripcion("Integer ID test");
        monitoreo.setEstado(true);

        assert monitoreo.getId() == 123456789;
        assert "Integer ID test".equals(monitoreo.getDescripcion());
        assert monitoreo.getEstado() == true;
    }

    @Test
    public void testMonitoreoWithBooleanState() {
        Monitoreo monitoreo = new Monitoreo();
        monitoreo.setId(1);
        monitoreo.setDescripcion("Boolean state test");
        monitoreo.setEstado(Boolean.TRUE);

        assert monitoreo.getId() == 1L;
        assert "Boolean state test".equals(monitoreo.getDescripcion());
        assert monitoreo.getEstado() == Boolean.TRUE;
    }

    @Test
    public void testMonitoreoWithNonNullValues() {
        Monitoreo monitoreo = new Monitoreo();
        monitoreo.setId(1);
        monitoreo.setDescripcion("Non-null values test");
        monitoreo.setEstado(Boolean.FALSE);

        assert monitoreo.getId() == 1L;
        assert "Non-null values test".equals(monitoreo.getDescripcion());
        assert monitoreo.getEstado() == Boolean.FALSE;
    }

    @Test
    public void testMonitoreoWithSpecialCharactersInDescription() {
        Monitoreo monitoreo = new Monitoreo();
        monitoreo.setId(1);
        monitoreo.setDescripcion("Special characters !@#$%^&*()");
        monitoreo.setEstado(true);

        assert monitoreo.getId() == 1L;
        assert "Special characters !@#$%^&*()".equals(monitoreo.getDescripcion());
        assert monitoreo.getEstado() == true;
    }

    @Test
    public void testMonitoreoWithLongDescription() {
        Monitoreo monitoreo = new Monitoreo();
        String longDescription = "This is a very long description that exceeds the usual length for testing purposes. "
                + "It should still be handled correctly by the Monitoreo class.";
        monitoreo.setId(1);
        monitoreo.setDescripcion(longDescription);
        monitoreo.setEstado(true);

        assert monitoreo.getId() == 1L;
        assert longDescription.equals(monitoreo.getDescripcion());
        assert monitoreo.getEstado() == true;
    }

    @Test
    public void testEqualsWithSelf() {
        Monitoreo monitoreo = new Monitoreo();
        monitoreo.setId(1);
        monitoreo.setDescripcion("Self equality test");
        monitoreo.setEstado(true);

        assert monitoreo.equals(monitoreo);
    }

    @Test
    public void testEqualsWithNullFields() {
        Monitoreo monitoreo1 = new Monitoreo();
        monitoreo1.setId(null);
        monitoreo1.setDescripcion(null);
        monitoreo1.setEstado(null);

        Monitoreo monitoreo2 = new Monitoreo();
        monitoreo2.setId(null);
        monitoreo2.setDescripcion(null);
        monitoreo2.setEstado(null);

        assert monitoreo1.equals(monitoreo2);
        assert monitoreo1.hashCode() == monitoreo2.hashCode();
    }

    @Test
    public void testEqualsWithDifferentType() {
        Monitoreo monitoreo = new Monitoreo();
        monitoreo.setId(1);
        monitoreo.setDescripcion("Different type test");
        monitoreo.setEstado(true);

        String notMonitoreo = "Not a Monitoreo";
        assert !monitoreo.equals(notMonitoreo);
    }

    @Test
    public void testHashCodeWithNullFields() {
        Monitoreo monitoreo1 = new Monitoreo();
        monitoreo1.setId(null);
        monitoreo1.setDescripcion(null);
        monitoreo1.setEstado(null);

        Monitoreo monitoreo2 = new Monitoreo();
        monitoreo2.setId(null);
        monitoreo2.setDescripcion(null);
        monitoreo2.setEstado(null);

        assert monitoreo1.hashCode() == monitoreo2.hashCode();
    }

    @Test
    public void testEqualsWithOnlyIdDifferent() {
        Monitoreo monitoreo1 = new Monitoreo();
        monitoreo1.setId(1);
        monitoreo1.setDescripcion("Desc");
        monitoreo1.setEstado(true);

        Monitoreo monitoreo2 = new Monitoreo();
        monitoreo2.setId(2);
        monitoreo2.setDescripcion("Desc");
        monitoreo2.setEstado(true);

        assert !monitoreo1.equals(monitoreo2);
    }

    @Test
    public void testEqualsWithOnlyDescripcionDifferent() {
        Monitoreo monitoreo1 = new Monitoreo();
        monitoreo1.setId(1);
        monitoreo1.setDescripcion("Desc1");
        monitoreo1.setEstado(true);

        Monitoreo monitoreo2 = new Monitoreo();
        monitoreo2.setId(1);
        monitoreo2.setDescripcion("Desc2");
        monitoreo2.setEstado(true);

        assert !monitoreo1.equals(monitoreo2);
    }

    @Test
    public void testEqualsWithOnlyEstadoDifferent() {
        Monitoreo monitoreo1 = new Monitoreo();
        monitoreo1.setId(1);
        monitoreo1.setDescripcion("Desc");
        monitoreo1.setEstado(true);

        Monitoreo monitoreo2 = new Monitoreo();
        monitoreo2.setId(1);
        monitoreo2.setDescripcion("Desc");
        monitoreo2.setEstado(false);

        assert !monitoreo1.equals(monitoreo2);
    }

}
