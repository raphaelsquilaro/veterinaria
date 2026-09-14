package sp.senai.org.vetmark.config;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import sp.senai.org.vetmark.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;


    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            PasswordEncoder passwordEncoder
    ) {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(
                        userDetailsService
                );

        provider.setPasswordEncoder(
                passwordEncoder
        );

        return provider;

    }


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration
                .getAuthenticationManager();

    }


    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            DaoAuthenticationProvider authenticationProvider
    ) throws Exception {
        http

                .authenticationProvider(
                        authenticationProvider
                )

                .authorizeHttpRequests(
                        auth -> auth

                                .requestMatchers(
                                        "/",
                                        "/login",
                                        "/css/**",
                                        "/images/**",
                                        "/js/**"

                                )
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )

                .formLogin(
                        form -> form

                                .loginPage("/")

                                .loginProcessingUrl(
                                        "/login"
                                )

                                .usernameParameter(
                                        "email"
                                )

                                .passwordParameter(
                                        "senha"
                                )

                                .defaultSuccessUrl(
                                        "/dashboard",
                                        true
                                )

                                .failureUrl(
                                        "/?error=true"
                                )

                                .permitAll()
                )

                .logout(
                        logout -> logout

                                .logoutUrl(
                                        "/logout"
                                )

                                .logoutSuccessUrl(
                                        "/?logout=true"
                                )

                                .invalidateHttpSession(
                                        true
                                )

                                .deleteCookies(
                                        "JSESSIONID"
                                )

                                .permitAll()
                );

        return http.build();

    }
}
