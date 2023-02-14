package mx.com.gm.dao;

import mx.com.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * En esta ocasion usamos JpaRepository en vez de CrudRepository
 * dado que este tiene mejoras
 */
public interface UsuarioDao extends JpaRepository<Usuario, Long> {

    //Metodo propio
    Usuario findByUsername(String username);

}
