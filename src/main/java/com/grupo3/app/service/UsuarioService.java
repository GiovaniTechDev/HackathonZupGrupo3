package com.grupo3.app.service;


import com.grupo3.app.Controller.form.TalentoFormDto;
import com.grupo3.app.Controller.form.UsuarioFormDto;
import com.grupo3.app.exception.EntityNotFoundBusinessException;
import com.grupo3.app.mapper.UsuarioMapper;
import com.grupo3.app.model.Recrutador;
import com.grupo3.app.model.Talento;
import com.grupo3.app.model.Usuario;
import com.grupo3.app.model.enuns.TipoUsuario;
import com.grupo3.app.repository.usuario.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    public TalentoFormDto toTalentoFormDto(Usuario usuario) {
        return usuarioMapper.toTalentoFormDto(usuario);
    }

    public Usuario toModel(UsuarioFormDto usuarioFormDto) {
        return usuarioMapper.toModel(usuarioFormDto);
    }

    @Transactional(readOnly = true)
    public Usuario getUsuarioById(UUID id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundBusinessException("Usuário não encontrado"));
    }

    @Transactional
    public void save(Usuario usuario) {

        if (usuario.isNew()) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            usuario.setCriadoEm(LocalDateTime.now());
            System.out.println("USUARIO: " + usuario);
        } else {
            Usuario usuarioSaved = getUsuarioById(usuario.getId());
            if (usuarioSaved.getSenha() != null && !usuarioSaved.getSenha().equals(usuario.getSenha())) {
                usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            }
            usuario.setCriadoEm(usuarioSaved.getCriadoEm());
        }
        usuario.setAtualizadoEm(LocalDateTime.now());

        usuario = verificaTipoUsuario(usuario);

        usuarioRepository.save(usuario);
    }

    private Usuario verificaTipoUsuario(Usuario usuario) {
        if (usuario.getTipoUsuario() == TipoUsuario.TALENTO) {
            usuario = modelMapper.map(usuario, Talento.class);
        } else if (usuario.getTipoUsuario() == TipoUsuario.RECRUTADOR) {
            usuario = modelMapper.map(usuario, Recrutador.class);
        }
        return usuario;
    }

}
