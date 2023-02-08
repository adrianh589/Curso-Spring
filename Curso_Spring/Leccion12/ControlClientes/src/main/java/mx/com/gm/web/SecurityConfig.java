package mx.com.gm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration // Indicar que es una clase configurable
@EnableWebSecurity // Habilitar la seguridad web
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService; // Se llama asi el objeto porque fue asi como se configuro en el servicio

    @Bean // Los bean sirven para que spring pueda usar directamente este metodo
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired // Metodo
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/editar/**", "/agregar/**", "/eliminar")
                    .hasRole("ADMIN")
                .antMatchers("/")
                    .hasAnyRole("USER", "ADMIN")
                .and()
                    .formLogin() // Con esto indicamos que pondremos un login personalizado
                    .loginPage("/login")// Aqui se pone el nombre del archivo HTML el cual contendra el login
                .and()
                .exceptionHandling().accessDeniedPage("/errores/403")// Especificar a security cual es nuestra pagina de error
        ;
    }

}
