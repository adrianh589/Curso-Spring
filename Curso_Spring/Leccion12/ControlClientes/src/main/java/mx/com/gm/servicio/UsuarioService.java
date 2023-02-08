package mx.com.gm.servicio;

import lombok.extern.slf4j.Slf4j;
import mx.com.domain.Rol;
import mx.com.domain.Usuario;
import mx.com.gm.dao.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * NOTA: Los metodos en los servicios DEBEN ser transaccionales
 * si no se coloca la anotacion, generara error debido
 * a que no puede recuperar el objeto
 */

@Service("userDetailsService") // Este nombre es para que lo use springsecurity
@Slf4j // para logs
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);

        if(usuario == null) {
            throw new UsernameNotFoundException(username);
        }

        var roles = new ArrayList<GrantedAuthority>();

        for (Rol rol:
             usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }

        return new User(usuario.getUsername(), usuario.getPassword(), roles); // Clase de spring
    }
}
