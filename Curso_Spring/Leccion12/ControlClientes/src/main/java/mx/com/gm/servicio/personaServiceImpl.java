package mx.com.gm.servicio;

import mx.com.domain.Persona;
import mx.com.gm.dao.PersonaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Como estamos en la CAPA DE SERVICIO de la aplicacion tiene que ver el tema de transacciones
// exitosas como en las bases de datos, en el dado caso de que una transaccion falle
// este hace un rollback, en dado caso de que sea exitosa hace un commit de toda la transaccion de
// todas las tablas afectadas

// NOTA: Todo lo que altere la base de datos ya sea una tabla por ejemplo, se considera una transaccion

@Service // PAra que spring reconozca esta clase como una notacion de servicio y es importante porque si
// no se coloca entonces no podemos inyectar esta clase como una implementacion de la interfaz de persona service
// dentro del controlador de spring
public class personaServiceImpl implements PersonaService{

    @Autowired // En el servicio se hace el desacople CAPA DE DATOS , que vendria siendo esta variable de personaDao
    private PersonaDao personaDao; // No fue necesario crear una clase concreta, ya que la interfaz extiende deC cRUD repository

    @Override
    @Transactional(readOnly = true) // Como vamos a leer datos, usamos esta anotacion la cual debe ser importada de spring
    public List<Persona> listarPersonas() {
        System.out.println(personaDao.findAll());
        return (List<Persona>) personaDao.findAll();
    }

    @Override
    @Transactional // Este no lleva parametro como el anterior metodo porque no vamos
    // a alterar información, y por el hecho de hacer guardado de información debe hacer commit o
    // rollback.
    public void guardar(Persona persona) {
        personaDao.save(persona);
    }

    @Override
    @Transactional
    public void eliminar(Persona persona) {
        personaDao.delete(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona encontrarPersona(Persona persona) {
        // Es de tipo optional porque puede que llegue null, entonces toca decidir que se hace
        // cuando el valor regresado sea de tipo null, el orElse hace que regrese null
        return personaDao.findById(persona.getIdPersona()).orElse(null);
    }
}
