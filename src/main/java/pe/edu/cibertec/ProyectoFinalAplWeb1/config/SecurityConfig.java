package pe.edu.cibertec.ProyectoFinalAplWeb1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // definir rutas protegidas y quien puede acceder a ellas
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/manage/login").permitAll() // rutas con acceso no autenticado
                        .requestMatchers("/manage/start").hasAnyRole("ADMIN") // rutas para ADMIN
                        .requestMatchers("/manage/add").hasAnyRole("ADMIN") // acceso para ADMIN unicamente
                        .requestMatchers("").hasAnyRole("USER")
                        .anyRequest().authenticated() // Esta línea asegura que cualquier otra solicitud
                                                      // que no haya sido previamente configurada
                                                      // debe requerir autenticación.
                )

                // redireccionar a una pagina en caso no se tenga permisos
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler((req, resp, excep) -> {
                            resp.sendRedirect("/manage/restricted");
                        })
                )

                // configurar formulario de inicio de sesion
                .formLogin(form -> form
                        .loginPage("/manage/login")
                        .defaultSuccessUrl("/manage/start", false)
                        .permitAll()
                )

                // configurar logout
                .logout(logout -> logout
                        .logoutUrl("/manage/logout")
                        .logoutSuccessUrl("/manage/login?logout")
                        .permitAll()
                );

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
