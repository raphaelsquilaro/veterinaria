package sp.senai.org.vetmark.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import sp.senai.org.vetmark.model.entity.Usuario;
import sp.senai.org.vetmark.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService
        implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(
            String email
    ) throws UsernameNotFoundException {

        Usuario usuario =
                usuarioRepository
                        .findByEmail(email)
                        .orElseThrow(
                                () ->
                                        new UsernameNotFoundException(
                                                "Usuário não encontrado"
                                        )
                        );


        return User.builder()

                .username(
                        usuario.getEmail()
                )

                .password(
                        usuario.getSenha()
                )

                .authorities(
                        "ROLE_" + usuario.getRole().name()
                )

                .disabled(
                        !usuario.getAtivo()
                )

                .build();

    }
}
