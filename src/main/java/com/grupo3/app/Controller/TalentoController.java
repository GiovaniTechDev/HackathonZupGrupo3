package com.grupo3.app.Controller;

import com.grupo3.app.Controller.form.TalentoFormDto;
import com.grupo3.app.model.enuns.TipoDeficiencia;
import com.grupo3.app.security.AppUser;
import com.grupo3.app.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/talento")
@AllArgsConstructor
public class TalentoController {

//    private final TalentoRepository talentoRepository;

//    private final TalentoService talentoService;

    private final UsuarioService usuarioService;

    @GetMapping("/detalhes-conta")
    public ModelAndView viewFormDetalhesContaTalento(TalentoFormDto talentoFormDto, @AuthenticationPrincipal AppUser appUser) {
        ModelAndView modelAndView = new ModelAndView("talento/detalhes-conta");
        modelAndView.addObject("talentoFormDto", talentoFormDto);
        modelAndView.addObject("tiposDeDeficiencias", TipoDeficiencia.values());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView editTalento(@PathVariable("id") UUID id, @AuthenticationPrincipal AppUser appUser) {
        return viewFormDetalhesContaTalento(usuarioService.toTalentoFormDto(usuarioService.getUsuarioById(id)), appUser);
    }

}
