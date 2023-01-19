package mx.com.domain;

import lombok.Data;

/**
 * Lo normal es crear getters y setters, pero como tenemos lombok, no es necesario crearlos,
 * en su lugar ponemos la anotacion @Data
 */
@Data
public class Persona {
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
}
