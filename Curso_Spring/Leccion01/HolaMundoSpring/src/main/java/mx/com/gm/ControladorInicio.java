package mx.com.gm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * NOTA: Todo lo que ste dentro del paquete mx.com.gm sera leido como clase para que spring lo pueda leer,
 * tener en cuenta que HolaMundoSpringApplication.java es el archivo principal de spring y por ende la
 * anotación de @SpringBootApplication nos ayuda a verificar que puede ser anotado con anotaciones
 * para que Java o Spring lo puedan leer y de esa manera tener un control de las clases que se vayan creando.
 */

@RestController
@Slf4j // Enviar informacion al log, esto es de lombok
public class ControladorInicio {

    // Para indicarle al navegador que este es el metodo que se debe ejecutar, se le debe poner la siguiente anotacion
    // Para poder mapearlo a un path y entre parentesis usamos la ruta que vamos a utilizar.:
    // Nota: Al poner GetMApping estamos indicando que el recutso es de tipo GET
    @GetMapping("/")
    public String inicio(){
        log.info("Ejecutando el controlador REST");
        log.debug("Mas detalles con el debug que se cambio en el application.properties");
        return "Hola mundo con Spring Boot";
    }
}
