package cl.ecomarket.monitoreo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * La clase Monitoreo es una entidad JPA que representa un registro de monitoreo en la base de datos.
 * Contiene un identificador único, una descripción y un estado que indica si el monitoreo esta activo o inactivo.
 */

@Entity
@Table(name = "monitoreo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Monitoreo {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 200)
    private String descripcion;

    @Column(nullable = false)
    private Boolean estado;
}
