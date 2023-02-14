package mx.com.gm.dao;

import mx.com.domain.Persona;
import org.springframework.data.repository.CrudRepository;

// Con este crudrepository se simplifica demasiado el codigo, por lo que no se debe hacer el tipico
// CRUD osea insert, delete, update, create, list, etc...
// y entre < > especificamos los tipos de objeto que va a manejar
// < Tipo de objeto, tipo de la llave primaria del objeto >
public interface PersonaDao extends CrudRepository<Persona, Long> {

    // Aparte de los tipicos metodos CRUD de CrudRepository, es aqui donde puedo poner mis querys personalizados
    // por ejemplo traer los usuarios con el apellido de Hoyos

}
