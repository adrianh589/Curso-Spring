package mx.com.gm.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // La forma de acceder a X urls se les conoce como autorizacion
         http.csrf().disable().authorizeHttpRequests()
                .requestMatchers("/editar/**", "/agregar/**", "/eliminar", "/guardar")
                    .hasRole("ADMIN")
                .requestMatchers("/")
                    .hasAnyRole("USER", "ADMIN")
                .and()
                    .formLogin();

                return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() { // La forma de agregar nuevos usuarios se le conoce como autenticacion
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("ADMIN")
                        .build();

        UserDetails user2 =
                User.withDefaultPasswordEncoder()
                        .username("user2")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user, user2);
    }

}
