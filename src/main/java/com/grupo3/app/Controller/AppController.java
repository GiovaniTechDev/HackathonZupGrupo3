package com.grupo3.app.Controller;

import com.grupo3.app.Controller.form.UsuarioFormDto;
import com.grupo3.app.model.Usuario;
import com.grupo3.app.model.enuns.TipoUsuario;
import com.grupo3.app.service.UsuarioService;
import com.grupo3.app.validation.group.ValidationGroupSequence;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class AppController {

    private final UsuarioService usuarioService;

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("app/home");
    }

    @GetMapping("/registro")
    public ModelAndView viewFormRegistro(UsuarioFormDto usuarioFormDto) {
        ModelAndView modelAndView = new ModelAndView("app/registro");
        modelAndView.addObject("tiposDeUsuarios", TipoUsuario.values());
        modelAndView.addObject("usuarioFormDto", usuarioFormDto);
        return modelAndView;
    }

    @PostMapping("/registro")
    public ModelAndView saveUsuario(@Validated(ValidationGroupSequence.class) UsuarioFormDto usuarioFormDto, BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return viewFormRegistro(usuarioFormDto);
        }

        Usuario usuario = usuarioService.toModel(usuarioFormDto);
        usuarioService.save(usuario);

        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("bi", "check-circle");
        redirectAttributes.addFlashAttribute("message", "Registrado com sucesso");

        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/candidaturas")
    public ModelAndView minhasCandidaturas() {
        return new ModelAndView("app/candidaturas");
    }

    @GetMapping("/detalhes-conta-recrutador")
    public ModelAndView detalhesContaRecrutador() {
        return new ModelAndView("app/detalhes-conta-recrutador");
    }

    @GetMapping("/login")
    public String viewFormLogin(@AuthenticationPrincipal User user) {
        if (user == null) {
            return "app/login";
        }
        return "redirect:/";
    }

}
