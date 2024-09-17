package com.grupo3.app.security;

import com.grupo3.app.model.Usuario;
import com.grupo3.app.repository.usuario.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmailIgnoreCaseAndAtivoTrue(email);
        Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
        return new AppUser(usuario, getPermissions(usuario));
    }

    private Collection<? extends GrantedAuthority> getPermissions(Usuario usuario) {
        return new HashSet<SimpleGrantedAuthority>();
    }

}
