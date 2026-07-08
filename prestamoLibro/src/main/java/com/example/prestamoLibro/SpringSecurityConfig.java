package com.example.prestamoLibro;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.prestamoLibro.Model.Service.UsuarioDetailService;

@Configuration
public class SpringSecurityConfig {

    private final  UsuarioDetailService usuarioDetailService;

    public SpringSecurityConfig(UsuarioDetailService usuarioDetailService) {
        this.usuarioDetailService = usuarioDetailService;
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
    //Pata utilizar este metodo se deben tener : BCPasswordEncoder y el usuarioDetailService
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

   @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
            .requestMatchers("/", "/css/**", "/js/**", "/img/**", "/prestamolibro/usuarioslistar").permitAll()
            .requestMatchers("/prestamolibro/usuarioconsultar/**").hasAnyRole("ADMIN","USER")
            .requestMatchers("/prestamolibro/prestamonuevo/**").hasAnyRole("ADMIN")
            .anyRequest().authenticated()
        )
        .formLogin(login -> login
            .loginPage("/login")
            .defaultSuccessUrl("/?loginSuccess", true)
            .permitAll()
        )
        .logout(logout -> logout
            .permitAll()
        )
        .exceptionHandling(ex -> ex
            .accessDeniedPage("/error_403")
        )
        .exceptionHandling(ex -> ex
	        .accessDeniedPage("/error_403")
        );

        return http.build();
    } 
}
