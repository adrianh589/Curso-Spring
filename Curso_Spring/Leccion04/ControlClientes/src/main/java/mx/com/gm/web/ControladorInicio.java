package mx.com.gm.web;

import lombok.extern.slf4j.Slf4j;
import mx.com.gm.servicio.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// NOTA: Los controladores son la CAPA DE PRESENTACION

@Controller // Ahora es un spring mvc
@Slf4j // Enviar informacion al log, esto es de lombok
public class ControladorInicio { // Al usasr la anotacion @Controller por debajo usa los servlets de java

    // @Autowired // Este autowired sirve para inyectar cualquier dependencia o cualquier objeto para que sea administrado por el contenedor
    // private PersonaDao personaDao; // Dado que ya tenemos un servicio, ahora vamos a inyectar el servicio en vez de persona DAO

    @Autowired
    private PersonaService personaService;

    // Para indicarle al navegador que este es el metodo que se debe ejecutar, se le debe poner la siguiente anotacion
    // Para poder mapearlo a un path y entre parentesis usamos la ruta que vamos a utilizar.:
    // Nota: Al poner GetMApping estamos indicando que el recutso es de tipo GET
    @GetMapping("/")
    public String inicio(Model model){
        log.info("Ejecutando el controlador spring mvc");
        // Traer la información de la base de datos
        var personas = personaService.listarPersonas();
        model.addAttribute("listaPersonas", personas);
        return "index";// Esto debe contener el nombre exacto del archivo HTML para poder visualizar la pagina
    }
}
