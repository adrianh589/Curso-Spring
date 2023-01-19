package mx.com.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * Lo normal es crear getters y setters, pero como tenemos lombok, no es necesario crearlos,
 * en su lugar ponemos la anotacion @Data
 */
@Data
@Entity // Con esta anotacion convertimos esta clase en una entidad/tabla
@Table(name="persona") // Con esta anotacion deifnimos la tabla de la que queremos representar de la tabla NO ES NECESARIA
// pero aveces hay problemas con otros sistemas operativos por lo que es mejor agregarla.

// EJEMPLO DE ERROR: Esta clase empieza con mayuscula (Persona), y la tabla empieza en minuscula (persona)
// por lo que ese simple hecho puede presentarse para errores.
public class Persona implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id // Asi se mapea la llave primaria, indicando que es la llave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Forma de generar la llave primaria
    private Long idPersona;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
}
