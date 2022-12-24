package mx.com.gm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * NOTA: Todo lo que ste dentro del paquete mx.com.gm sera leido como clase para que spring lo pueda leer,
 * tener en cuenta que HolaMundoSpringApplication.java es el archivo principal de spring y por ende la
 * anotación de @SpringBootApplication nos ayuda a verificar que puede ser anotado con anotaciones
 * para que Java o Spring lo puedan leer y de esa manera tener un control de las clases que se vayan creando.
 */

@Controller // Ahora es un spring mvc
@Slf4j // Enviar informacion al log, esto es de lombok
public class ControladorInicio { // Al usasr la anotacion @Controller por debajo usa los servlets de java

    @Value("${index.saludo}") // Debe ser importado desde spring.framewrok, y la llamamos con la interpolacion
    private String saludo;// Ya esta variable toma el valor que se puso en application properties

    // Para indicarle al navegador que este es el metodo que se debe ejecutar, se le debe poner la siguiente anotacion
    // Para poder mapearlo a un path y entre parentesis usamos la ruta que vamos a utilizar.:
    // Nota: Al poner GetMApping estamos indicando que el recutso es de tipo GET
    @GetMapping("/")
    public String inicio(Model model){
        log.info("Ejecutando el controlador spring mvc");
        var mensaje = "Hola mundo con thymeleaf";
        model.addAttribute("mensaje", mensaje);// Con la clase model lo que haremos es compartir informacion a nuestra vista, es clave - valor
        model.addAttribute("saludoPropertie", saludo);
        return "index";// Esto debe contener el nombre exacto del archivo HTML para poder visualizar la pagina
    }
}
