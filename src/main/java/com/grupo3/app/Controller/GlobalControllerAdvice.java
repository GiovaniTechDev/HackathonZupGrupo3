package com.grupo3.app.Controller;

import com.grupo3.app.model.enuns.TipoDeficiencia;
import com.grupo3.app.model.enuns.TipoUsuario;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("tiposDeUsuarios")
    public List<TipoUsuario> tipoUsuarioList() {
        return List.of(TipoUsuario.values());
    }

    @ModelAttribute("tiposDeDeficiencias")
    public List<TipoDeficiencia> tipoDeficienciaList() {
        return List.of(TipoDeficiencia.values());
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(false);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

}
