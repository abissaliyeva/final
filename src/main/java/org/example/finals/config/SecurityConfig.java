package org.example.finals.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // FOR STUDY PROJECT ONLY
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(authorize -> authorize

                        /* ---------- PUBLIC ---------- */
                        .requestMatchers(
                                "/",
                                "/login",
                                "/api/users/register",
                                "/css/",
                                "/js/",
                                "/images/**"
                        ).permitAll()

                        /* ---------- ADMIN ---------- */
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/api/houses/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/api/subjects/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/api/users/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasAuthority("ROLE_ADMIN")

                        /* ---------- TEACHER ---------- */
                        .requestMatchers("/teacher/**").hasAuthority("ROLE_TEACHER")
                        .requestMatchers("/api/lessons/**").hasAuthority("ROLE_TEACHER")
                        .requestMatchers("/api/grades/assign").hasAuthority("ROLE_TEACHER")

                        /* ---------- STUDENT ---------- */
                        .requestMatchers("/student/**").hasAuthority("ROLE_STUDENT")
                        .requestMatchers("/api/grades/my").hasAuthority("ROLE_STUDENT")

                        /* ---------- AUTHENTICATED ---------- */
                        .requestMatchers("/profile").authenticated()

                        .anyRequest().authenticated()
                )
                /* ---------- LOGOUT ---------- */
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .deleteCookies("JSESSIONID")
                )

                /* ---------- ACCESS DENIED ---------- */
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/login?accessDenied")
                );

        return http.build();
    }
}
