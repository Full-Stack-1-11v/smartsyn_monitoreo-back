package cl.ecomarket.monitoreo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteDTO {
    private Integer id;
    private String tipo;
    private String fecha;

}
