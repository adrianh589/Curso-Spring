package mx.com.gm.web;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.domain.Persona;
import mx.com.gm.servicio.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String inicio(Model model, @AuthenticationPrincipal User user){
        log.info("Ejecutando el controlador spring mvc");
        log.info("usuario que hizo login:" + user);
        // Traer la información de la base de datos
        var personas = personaService.listarPersonas();
        model.addAttribute("listaPersonas", personas);
        return "index";// Esto debe contener el nombre exacto del archivo HTML para poder visualizar la pagina
    }

    @GetMapping("/agregar")
    public String agregar(Persona persona, Model model){ // no es necesario el operador new ya que spring lo hace de forma automatica
        model.addAttribute("persona", persona);
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores){ // Recibe el objeto del formulario, el valid sirve para validar este objeto segun los campos obligatorios que se pusieron en la clase Persona.java
        if (errores.hasErrors()){ // Si hemos recibido errores por parte de alguno de los campos
            return "modificar";
        }
        personaService.guardar(persona);
        return "redirect:/"; // Asi redireccionamos a la pagina de inicio
    }

    @GetMapping("/editar/{idPersona}") // Spring automaticamente inicializara el objeto de tipo persona con el paramero de idPersona llamando el metodo setIdPersona que esta en la clase de Persona
    public String editar(Persona persona, Model model){
        var personaIndex = personaService.encontrarPersona(persona);
        model.addAttribute("persona",personaIndex);
        log.info("Este es el objeto persona");
        return "modificar";
    }

    @GetMapping("/eliminar/{idPersona}")
    public String eliminar(Persona persona){ // Este path ahora solo queda para la forma path variable
        personaService.eliminar(persona);
        return "redirect:/";
    }

    @GetMapping("/eliminar")// Ahora como el parametro va por query param entonces lo reconoce como una propiedad de tipo perosna por lo que se lo asina en automatico
    public String eliminarQP(Persona persona){ // Este path ahora solo queda para la forma query parameter
        personaService.eliminar(persona);
        return "redirect:/";
    }

}
