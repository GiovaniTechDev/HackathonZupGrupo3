package com.grupo3.app.mapper;

import com.grupo3.app.Controller.form.TalentoFormDto;
import com.grupo3.app.Controller.form.UsuarioFormDto;
import com.grupo3.app.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Component
public class UsuarioMapper {

    private final ModelMapper modelMapper;

    public UsuarioFormDto toFormDto(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioFormDto.class);
    }

    public Usuario toModel(UsuarioFormDto usuarioFormDto) {
        return modelMapper.map(usuarioFormDto, Usuario.class);
    }

    public TalentoFormDto toTalentoFormDto(Usuario usuario) {
        return modelMapper.map(usuario, TalentoFormDto.class);
    }

}
