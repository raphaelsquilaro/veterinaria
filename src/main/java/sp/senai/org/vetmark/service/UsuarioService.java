package sp.senai.org.vetmark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sp.senai.org.vetmark.model.entity.Usuario;
import sp.senai.org.vetmark.model.enums.Role;
import sp.senai.org.vetmark.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    public Usuario criarUsuarioInicial() {

        String email =
                "admin@vetcare.com";

        if (
                usuarioRepository
                        .existsByEmail(email)
        ) {
            return usuarioRepository
                    .findByEmail(email)
                    .orElseThrow();
        }

        Usuario usuario =
                Usuario.builder()
                        .nome(
                                "Administrador"
                        )
                        .email(
                                email
                        )
                        .senha(
                                passwordEncoder.encode(
                                        "Admin123!"
                                )
                        )
                        .role(
                                Role.ADMIN
                        )
                        .ativo(
                                true
                        )
                        .build();

        return usuarioRepository.save(usuario);
    }
}
