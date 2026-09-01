package sp.senai.org.vetmark.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sp.senai.org.vetmark.service.UsuarioService;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UsuarioService usuarioService;

    @Bean
    public CommandLineRunner
    initializeData() {
        return args -> {
            usuarioService.criarUsuarioInicial();
        };
    }
}
