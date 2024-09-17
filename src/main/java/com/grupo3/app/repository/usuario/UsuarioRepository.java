package com.grupo3.app.repository.usuario;

import com.grupo3.app.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>, UsuarioQueries {

    Optional<Usuario> findById(UUID id);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByEmailIgnoreCaseAndAtivoTrue(String email);

}
