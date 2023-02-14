package mx.com.gm.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration // Indicar que es una clase configurable
@EnableWebSecurity // Habilitar la seguridad web
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                    .password("{noop}123")
                    .roles("ADMIN", "USER")
                .and()
                .withUser("user")
                    .password("{noop}123")
                    .roles("USER");
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
